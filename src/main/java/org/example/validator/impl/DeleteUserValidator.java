package org.example.validator.impl;

import org.example.entity.User;
import org.example.validator.Validator;
import org.example.validator.exception.IllegalDeleteUserException;
import org.example.validator.exception.ValidatorException;

public class DeleteUserValidator implements Validator<User> {
    @Override
    public void validate(User user) throws ValidatorException {
        if (user.getOrders().size() > 0) {
            throw new IllegalDeleteUserException("cannot delete user with orders");
        }
    }
}
