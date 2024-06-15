package com.samsepiol.userprofile.exception;

import com.samsepiol.userprofile.model.Error;

public class DatabaseInsertException extends UserProfileException {

    protected DatabaseInsertException() {
        super(Error.DB_INSERTION_ERROR);
    }

    public static DatabaseInsertException build() {
        return new DatabaseInsertException();
    }
}
