package com.example.user.profile.dao.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MongoInsertOrUpdateRequest extends MongoBaseRequest {
    private final Object value;
}
