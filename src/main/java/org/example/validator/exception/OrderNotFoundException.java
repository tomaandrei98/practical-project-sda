package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class OrderNotFoundException extends StoreException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
