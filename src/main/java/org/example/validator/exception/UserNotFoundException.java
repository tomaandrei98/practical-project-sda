package org.example.validator.exception;

public class UserNotFoundException extends StoreException {
    public UserNotFoundException(String message) {
        super(message);
    }
}