package com.example.user.profile.library.httpclient;

import com.example.user.profile.library.httpclient.request.ApiRequest;

public interface HttpClient {
    <T> T execute(ApiRequest request, Class<T> responseCls);
}
