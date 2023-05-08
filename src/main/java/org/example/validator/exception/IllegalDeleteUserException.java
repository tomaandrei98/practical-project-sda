package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class IllegalDeleteUserException extends StoreException {
    public IllegalDeleteUserException(String message) {
        super(message);
    }
}
