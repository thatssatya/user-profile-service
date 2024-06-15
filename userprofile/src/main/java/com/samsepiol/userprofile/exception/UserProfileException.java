package com.samsepiol.userprofile.exception;

import com.samsepiol.userprofile.model.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UserProfileException extends RuntimeException {
    private final Error error;
}
