package com.java.authenticationservice.exceptions;

public class UserAlreadyExistsException extends Exception
{
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
