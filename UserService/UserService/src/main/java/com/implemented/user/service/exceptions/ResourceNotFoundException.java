package com.implemented.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not Found on server !!");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
