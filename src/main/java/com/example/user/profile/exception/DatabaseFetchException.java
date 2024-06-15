package com.example.user.profile.exception;

import com.example.user.profile.model.Error;

public class DatabaseFetchException extends UserProfileException {

    protected DatabaseFetchException() {
        super(Error.DB_FETCH_ERROR);
    }

    public static DatabaseFetchException build() {
        return new DatabaseFetchException();
    }
}
