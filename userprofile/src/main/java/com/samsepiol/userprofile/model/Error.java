package com.samsepiol.userprofile.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {
    INTERNAL_SERVER_ERROR(1, "Internal Server Error!"),
    SERIALIZATION_ERROR(2, "Serialization Error"),
    DB_INSERTION_ERROR(3, "Insertion To DB failed"),
    DB_FETCH_ERROR(4, "Fetch from DB failed"),
    USER_NOT_EXISTS(5, "User does not exist in system"),
    BAD_REQUEST(6, "Bad Request");


    private final Integer errorCode;
    private final String message;
}
