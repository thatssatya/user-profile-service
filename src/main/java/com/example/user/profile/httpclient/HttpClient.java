package com.example.user.profile.httpclient;

import com.example.user.profile.httpclient.request.ApiRequest;

public interface HttpClient {
    <T> T execute(ApiRequest request, Class<T> responseCls);
}
