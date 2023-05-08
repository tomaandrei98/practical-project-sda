package org.example.validator.exception;

import org.example.validator.exception.base.StoreException;

public class IllegalDeleteProductException extends StoreException {
    public IllegalDeleteProductException(String message) {
        super(message);
    }
}
