package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    boolean saveProduct(int userId, Product product);

    void updateProduct(int userId, int id, String title, String description, double price);

    Optional<Product> deleteProduct(int userId, Product product);

    Optional<Product> findProductById(int id);

    Optional<Product> findProductByTitle(String title);

    List<Product> findAllProducts();

    List<Product> findProductsByType(ProductType productType);

    List<Product> findProductsWithPriceBetween(double min, double max);
}
