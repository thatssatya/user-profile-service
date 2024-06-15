package com.example.user.profile.library.http.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApiRequest {
    @NonNull
    String service;
    @NonNull
    String api;
    Object body;
    @NonNull
    @Builder.Default
    String contentType = "application/json";
    String origin;
    String authorization;
}
