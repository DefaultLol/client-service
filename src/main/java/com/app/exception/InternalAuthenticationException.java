package com.app.exception;

public class InternalAuthenticationException extends RuntimeException {
    public InternalAuthenticationException(String message){
        super(message);
    }
}
