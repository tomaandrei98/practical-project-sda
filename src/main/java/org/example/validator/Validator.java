package org.example.validator;

import org.example.validator.exception.ValidatorException;

public interface Validator<E> {
    void validate(E entity) throws ValidatorException;
}
