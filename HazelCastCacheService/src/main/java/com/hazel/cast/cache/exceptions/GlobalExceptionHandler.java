package com.hazel.cast.cache.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(ResourceNotFoundException exception) {

        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .success("True")
                        .status(HttpStatus.NOT_FOUND)
                        .build();
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
