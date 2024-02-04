package com.example.user.profile.library.http.client;

import com.example.user.profile.library.http.request.ApiRequest;

public interface HttpClient {
    <T> T execute(ApiRequest request, Class<T> responseCls);
}
