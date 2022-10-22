package com.mediscreen.history.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String resource) {
        super("Resource doesn't exist : " + resource);
    }
}
