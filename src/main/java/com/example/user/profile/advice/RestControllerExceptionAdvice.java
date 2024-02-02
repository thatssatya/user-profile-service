package com.example.user.profile.advice;

import com.example.user.profile.exception.UserProfileException;
import com.example.user.profile.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ExceptionHandler(UserProfileException.class)
    public ResponseEntity<ErrorResponse> handleException(UserProfileException exception) {
        log.error("User Profile Exception: ", exception);
        return ResponseEntity
                .status(getStatus(exception))
                .body(ErrorResponse.from(exception.getError().getErrorCode(), exception.getError().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.badRequest());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Exception occurred: ", exception);
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.internalServerError());
    }

    private static HttpStatus getStatus(UserProfileException exception) {
        return switch (exception.getError()) {
            case USER_NOT_EXISTS ->  HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
