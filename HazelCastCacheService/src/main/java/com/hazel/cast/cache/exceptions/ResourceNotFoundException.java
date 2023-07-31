package com.hazel.cast.cache.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }

    public ResourceNotFoundException(){
        System.out.println(" User with this Id doesn't Exists!");
    }
}
