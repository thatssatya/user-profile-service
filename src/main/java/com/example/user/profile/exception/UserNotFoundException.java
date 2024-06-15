package com.example.user.profile.exception;

import com.example.user.profile.model.Error;

public class UserNotFoundException extends UserProfileException {

    protected UserNotFoundException() {
        super(Error.USER_NOT_EXISTS);
    }

    public static UserNotFoundException build() {
        return new UserNotFoundException();
    }
}
