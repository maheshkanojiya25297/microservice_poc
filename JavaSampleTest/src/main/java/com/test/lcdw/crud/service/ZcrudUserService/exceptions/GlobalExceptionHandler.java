package com.test.lcdw.crud.service.ZcrudUserService.exceptions;

import com.test.lcdw.crud.service.ZcrudUserService.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ApiResponse> handleException(ResourceNotFoundException ex) {
        ApiResponse response = ApiResponse.builder()
                .message(ex.getMessage()).success("true").status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
