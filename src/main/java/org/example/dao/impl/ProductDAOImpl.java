package org.example.dao.impl;

import org.example.dao.ProductDAO;
import org.example.dao.base.BaseDAO;
import org.example.entity.Product;
import org.example.entity.ProductType;
import org.example.entity.Role;
import org.example.validator.Validator;
import org.example.validator.exception.IllegalDeleteProductException;
import org.example.validator.exception.ValidatorException;
import org.example.validator.impl.ProductValidator;
import org.example.validator.impl.UserRoleValidator;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {
    private final Validator<Integer> userRoleValidator = new UserRoleValidator(currentSession, Role.ADMIN);
    private final Validator<Product> productValidator = new ProductValidator();

    public ProductDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean saveProduct(int userId, Product product) {
        boolean success = false;
        Transaction transaction = null;
        try {
            userRoleValidator.validate(userId);
            productValidator.validate(product);
            try {
                transaction = currentSession.beginTransaction();
                currentSession.persist(product);
                transaction.commit();
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    @Override
    public void updateProduct(int userId, int id, String title, String description, double price) {
        Transaction transaction = null;
        try {
            userRoleValidator.validate(userId);
            try {
                transaction = currentSession.beginTransaction();

                Query<Object> query = currentSession.createQuery(
                        String.format("""
                                update Product
                                set title = '%s', description = '%s', price = %s
                                where id = %s
                                """, title, description, price, id),
                        null);

                query.executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Product> deleteProduct(int userId, Product product) {
        Transaction transaction = null;
        try {
            userRoleValidator.validate(userId);
            productValidator.validate(product);
            try {
                transaction = currentSession.beginTransaction();
                currentSession.remove(product);
                transaction.commit();
                return Optional.ofNullable(product);
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (ValidatorException | IllegalDeleteProductException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductById(int id) {
        return Optional.ofNullable(currentSession.get(Product.class, id));
    }

    @Override
    public Optional<Product> findProductByTitle(String title) {
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();
            Query<Product> query = currentSession
                    .createQuery(String.format("from Product where title = '%s'", title), Product.class);

            transaction.commit();
            return query.uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Product> findAllProducts() {
        return currentSession.createQuery("from Product", Product.class).list();
    }

    @Override
    public List<Product> findProductsByType(ProductType productType) {
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();
            Query<Product> query = currentSession
                    .createQuery(String.format("from Product where productType = '%s'", productType), Product.class);

            transaction.commit();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return List.of();
    }

    @Override
    public List<Product> findProductsWithPriceBetween(double min, double max) {
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();
            Query<Product> query = currentSession
                    .createQuery(String.format("from Product where price between %s and %s", min, max), Product.class);

            transaction.commit();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return List.of();
    }
}
