package com.samsepiol.userprofile.exception;

import com.samsepiol.userprofile.model.Error;

public class UserNotFoundException extends UserProfileException {

    protected UserNotFoundException() {
        super(Error.USER_NOT_EXISTS);
    }

    public static UserNotFoundException build() {
        return new UserNotFoundException();
    }
}
