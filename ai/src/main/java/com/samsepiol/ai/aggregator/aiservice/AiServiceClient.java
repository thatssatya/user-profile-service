package com.samsepiol.ai.aggregator.aiservice;

import com.samsepiol.ai.aggregator.aiservice.config.AIClientConfig;
import com.samsepiol.ai.aggregator.aiservice.request.AIClientRequest;
import com.samsepiol.ai.aggregator.aiservice.response.AIResponse;
import com.samsepiol.bom.library.exception.LibraryException;
import com.samsepiol.bom.library.http.client.HttpClient;
import com.samsepiol.bom.library.http.request.ApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AiServiceClient {
    private static final String SERVICE = "aiService";
    private static final String CHAT_API = "chat";

    private final HttpClient httpClient;
    private final AIClientConfig aiClientConfig;

    public AIResponse getResponse(AIClientRequest request) {
        try {
            var apiRequest = ApiRequest.builder()
                    .service(SERVICE)
                    .api(CHAT_API)
                    .body(request)
                    .headers(aiClientConfig.getHeaders())
                    .build();

            return httpClient.execute(apiRequest, AIResponse.class);
        } catch (LibraryException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<AIResponse> getAsyncResponse(AIClientRequest request) {
        try {
            var apiRequest = ApiRequest.builder()
                    .service(SERVICE)
                    .api(CHAT_API)
                    .body(request)
                    .headers(aiClientConfig.getHeaders())
                    .build();

            return httpClient.executeAsync(apiRequest, AIResponse.class);
        } catch (LibraryException e) {
            throw new RuntimeException(e);
        }
    }
}
