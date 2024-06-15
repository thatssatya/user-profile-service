package com.samsepiol.bom.library.exception;

import com.samsepiol.bom.library.exception.enums.Error;

public class SerializationException extends LibraryException {

    protected SerializationException() {
        super(Error.SERIALIZATION_ERROR);
    }

    public static SerializationException build() {
        return new SerializationException();
    }
}
