package com.webapptest.webapptest.exception;

import com.webapptest.webapptest.exception.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException e) {
        ResponseError responseError = new ResponseError();
        responseError.setMessage(e.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
    }
}
