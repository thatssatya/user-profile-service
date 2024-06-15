package com.example.user.profile.exception;

import com.example.user.profile.model.Error;

public class SerializationException extends UserProfileException {

    protected SerializationException() {
        super(Error.SERIALIZATION_ERROR);
    }

    public static SerializationException build() {
        return new SerializationException();
    }
}
