package com.samsepiol.userprofile.dao.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MongoInsertOrUpdateRequest extends MongoBaseRequest {
    private final Object value;
}
