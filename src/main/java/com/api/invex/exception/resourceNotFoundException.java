package com.api.invex.exception;

public class resourceNotFoundException extends RuntimeException {
    public resourceNotFoundException(String message) {
        super(message);
    }
}
