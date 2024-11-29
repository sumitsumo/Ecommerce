package com.java.authenticationservice.exceptions;


public class SessionNotFoundException extends RuntimeException
{
    public SessionNotFoundException() {
    }

    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
