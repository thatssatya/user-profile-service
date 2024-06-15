package com.example.user.profile.exception;

import com.example.user.profile.model.Error;

public class DatabaseInsertException extends UserProfileException {

    protected DatabaseInsertException() {
        super(Error.DB_INSERTION_ERROR);
    }

    public static DatabaseInsertException build() {
        return new DatabaseInsertException();
    }
}
