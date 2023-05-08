package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class ValidatorException extends StoreException {
    public ValidatorException(String message) {
        super(message);
    }
}