package com.samsepiol.userprofile.exception;

import com.samsepiol.userprofile.model.Error;

public class DatabaseFetchException extends UserProfileException {

    protected DatabaseFetchException() {
        super(Error.DB_FETCH_ERROR);
    }

    public static DatabaseFetchException build() {
        return new DatabaseFetchException();
    }
}
