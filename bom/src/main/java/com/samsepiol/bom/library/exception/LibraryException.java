package com.samsepiol.bom.library.exception;

import com.samsepiol.bom.library.exception.enums.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class LibraryException extends Exception {
    Error error;
}
