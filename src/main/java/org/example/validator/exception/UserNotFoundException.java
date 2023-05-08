package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class UserNotFoundException extends StoreException {
    public UserNotFoundException(String message) {
        super(message);
    }
}