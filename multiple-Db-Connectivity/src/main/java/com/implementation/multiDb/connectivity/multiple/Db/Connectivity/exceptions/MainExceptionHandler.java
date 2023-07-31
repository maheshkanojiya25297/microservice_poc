package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.exceptions;

import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.exceptions.response.ExResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExResponse> exceptionHandle(ResourceNotFoundException resourceNotFoundException) {
        ExResponse response = ExResponse.builder().message(resourceNotFoundException.getMessage()).sucess("true").status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ExResponse>(response, HttpStatus.NOT_FOUND);

    }

}
