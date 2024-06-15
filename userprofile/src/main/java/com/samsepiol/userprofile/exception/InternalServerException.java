package com.samsepiol.userprofile.exception;

import com.samsepiol.userprofile.model.Error;

public class InternalServerException extends UserProfileException {

    protected InternalServerException() {
        super(Error.INTERNAL_SERVER_ERROR);
    }

    public static InternalServerException build() {
        return new InternalServerException();
    }
}
