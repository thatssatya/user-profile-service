package com.example.user.profile.httpclient.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class ApiRequest {
    @NonNull
    private final String service;
    @NonNull
    private final String api;
    private final Object body;
}
