package org.example.validator.impl;

import org.example.entity.Product;
import org.example.validator.Validator;
import org.example.validator.exception.IllegalDeleteProductException;
import org.example.validator.exception.ValidatorException;

public class ProductValidator implements Validator<Product> {
    @Override
    public void validate(Product product) throws ValidatorException {
        if (product.getPrice() <= 0) {
            throw new ValidatorException("price cannot be negative");
        }

        if (product.getOrders().size() > 0) {
            throw new IllegalDeleteProductException("cannot delete product that appear in orders");
        }
    }
}