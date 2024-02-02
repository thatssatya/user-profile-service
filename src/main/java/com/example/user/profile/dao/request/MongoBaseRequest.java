package com.example.user.profile.dao.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@SuperBuilder(builderMethodName = "parentBuilder")
public class MongoBaseRequest {
    @NotBlank
    private final String collectionName;
    @NotBlank
    private final String key;
}
