package com.example.user.profile.exception;

import com.example.user.profile.model.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserProfileException extends RuntimeException {
    private final Error error;

    public static UserProfileException fromErrorCode(Error error) {
        return new UserProfileException(error);
    }
}
