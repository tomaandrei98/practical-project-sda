package org.example.service.impl;

import org.example.dao.ProductDAO;
import org.example.dao.impl.ProductDAOImpl;
import org.example.entity.Product;
import org.example.entity.ProductType;
import org.example.service.ProductService;
import org.example.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl extends BaseService implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl(getSessionFactory());
    }

    @Override
    public boolean saveProduct(int userId, Product product) {
        return productDAO.saveProduct(userId, product);
    }

    @Override
    public void updateProduct(int userId, int id, String title, String description, double price) {
        productDAO.updateProduct(userId, id, title, description, price);
    }

    @Override
    public Optional<Product> deleteProduct(int userId, Product product) {
        return productDAO.deleteProduct(userId, product);
    }

    @Override
    public Optional<Product> findProductById(int id) {
        return productDAO.findProductById(id);
    }

    @Override
    public Optional<Product> findProductByTitle(String title) {
        return productDAO.findProductByTitle(title);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Override
    public List<Product> findProductsByType(ProductType productType) {
        return productDAO.findProductsByType(productType);
    }

    @Override
    public List<Product> findProductsWithPriceBetween(double min, double max) {
        return productDAO.findProductsWithPriceBetween(min, max);
    }
}
