package com.example.user.profile.model.response;

import com.example.user.profile.model.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -289285870373810184L;

    private final Integer errorCode;
    private final String message;
    private final String displayMessage;

    public static ErrorResponse from(Integer errorCode, String message) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }

    public static ErrorResponse from(Error error) {
        return from(error.getErrorCode(), error.getMessage());
    }

    public static ErrorResponse internalServerError() {
        return from(Error.INTERNAL_SERVER_ERROR);
    }

}