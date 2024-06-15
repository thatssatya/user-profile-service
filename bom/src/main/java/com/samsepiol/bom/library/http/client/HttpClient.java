package com.samsepiol.bom.library.http.client;

import com.samsepiol.bom.library.exception.LibraryException;
import com.samsepiol.bom.library.http.request.ApiRequest;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    <T> T execute(ApiRequest request, Class<T> responseCls) throws LibraryException;

    <T> CompletableFuture<T> executeAsync(ApiRequest request, Class<T> responseCls) throws LibraryException;
}
