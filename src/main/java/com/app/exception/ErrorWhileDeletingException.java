package com.app.exception;

public class ErrorWhileDeletingException extends RuntimeException {
    public ErrorWhileDeletingException(String message){
        super(message);
    }
}
