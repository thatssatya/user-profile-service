package com.example.user.profile.library.http.client;

import com.example.user.profile.library.http.request.ApiRequest;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    <T> T execute(ApiRequest request, Class<T> responseCls);

    <T> CompletableFuture<T> executeAsync(ApiRequest request, Class<T> responseCls);
}
