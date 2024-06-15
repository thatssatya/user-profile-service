package com.example.user.profile.exception;

import com.example.user.profile.model.Error;

public class InternalServerException extends UserProfileException {

    protected InternalServerException() {
        super(Error.INTERNAL_SERVER_ERROR);
    }

    public static InternalServerException build() {
        return new InternalServerException();
    }
}
