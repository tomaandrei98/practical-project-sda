package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class ProductNotFoundException extends StoreException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
