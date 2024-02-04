package com.example.user.profile.library.http.client.impl;

import com.example.user.profile.exception.UserProfileException;
import com.example.user.profile.library.http.client.HttpClient;
import com.example.user.profile.library.http.config.HttpConfig;
import com.example.user.profile.library.http.request.ApiRequest;
import com.example.user.profile.model.Error;
import com.example.user.profile.util.SerializationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
@Primary
@Slf4j
public class DefaultHttpClient implements HttpClient {
    private final HttpConfig httpConfig;
    private static final java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

    @Override
    public <T> T execute(ApiRequest request, Class<T> responseCls) {
        try {
            log.info("Sending http request...");
            var response = client.send(buildHttpRequest(request), HttpResponse.BodyHandlers.ofString());

            log.info("Response received: {}", response.body());
            return deserializedResponse(responseCls, response.body());
        } catch (UserProfileException exception) {
            throw exception;
        } catch (Exception exception) {
            log.error("Exception occurred while executing api request: ", exception);
            throw UserProfileException.fromErrorCode(Error.INTERNAL_SERVER_ERROR);
        }
    }

    private static <T> T deserializedResponse(Class<T> responseCls, Object response) {
        return SerializationUtil.convertToEntity(response, responseCls);
    }

    private HttpRequest buildHttpRequest(ApiRequest request) {
        var builder = HttpRequest
                .newBuilder(URI.create(buildUrl(request)))
                .header("Content-Type", "application/json");

        if (isPostApi(request)) {
            builder = builder.POST(HttpRequest.BodyPublishers.ofString(serializedRequestBody(request.getBody())));
        } else if (isGetApi(request)) {
            builder = builder.GET();
        } else if (isPatchApi(request)) {
            builder = builder.PUT(HttpRequest.BodyPublishers.ofString(serializedRequestBody(request.getBody())));
        } else if (isDeleteApi(request)) {
            builder = builder.DELETE();
        }

        return builder.build();
    }

    private boolean isDeleteApi(ApiRequest request) {
        return httpConfig.getServiceConfig(request.getService()).getApiConfig(request.getApi()).isDELETE();
    }

    private boolean isPatchApi(ApiRequest request) {
        return httpConfig.getServiceConfig(request.getService()).getApiConfig(request.getApi()).isPATCH();
    }

    private boolean isGetApi(ApiRequest request) {
        return httpConfig.getServiceConfig(request.getService()).getApiConfig(request.getApi()).isGET();
    }

    private boolean isPostApi(ApiRequest request) {
        return httpConfig.getServiceConfig(request.getService()).getApiConfig(request.getApi()).isPOST();
    }

    private String buildUrl(ApiRequest request) {
        var protocol = buildProtocol(request);
        var baseUrl = httpConfig.getServiceConfig(request.getService()).getBaseUrl();
        var api = httpConfig.getServiceConfig(request.getService()).getApiConfig(request.getApi()).getPath();

        log.info(protocol, baseUrl, api);

        return String.format("%s%s%s", protocol, baseUrl, api);
    }

    private String buildProtocol(ApiRequest request) {
        return httpConfig.getServiceConfig(request.getService()).isSecured() ? "https://" : "http:";
    }

    private static String serializedRequestBody(Object object) {
        return SerializationUtil.convertToString(object);
    }
}
