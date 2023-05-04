package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.dao.base.BaseDAO;
import org.example.entity.*;
import org.example.validator.Validator;
import org.example.validator.exception.ValidatorException;
import org.example.validator.impl.UserRoleValidator;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl extends BaseDAO implements OrderDAO {
    private final Validator<Integer> userRoleValidator = new UserRoleValidator(currentSession, Role.REGULAR_USER);

    public OrderDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean saveOrder(int clientId, Order order, List<Integer> productsId) {
        boolean success = false;
        Transaction transaction = null;
        try {
            userRoleValidator.validate(clientId);
            try {
                transaction = currentSession.beginTransaction();

                mapUserToOrder(clientId, order);
                mapOrderToProducts(order, productsId);

                currentSession.persist(order);
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

    private void mapOrderToProducts(Order order, List<Integer> productsId) {
        // map products - order
        productsId.forEach(id -> {
            Product product = currentSession.createQuery(String.format("""
                    from Product where id = %s
                    """, id), Product.class).getSingleResult();

            order.getProducts().add(product);
            product.getOrders().add(order);
        });
    }

    private void mapUserToOrder(int clientId, Order order) {
        // map order - user
        User user = currentSession.createQuery(String.format("""
                from User where id = %s
                """, clientId), User.class).getSingleResult();
        user.getOrders().add(order);
    }

    @Override
    public void updateStatusForOrder(int id, OrderStatus orderStatus) {
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();

            Query<Object> query = currentSession.createQuery(
                    String.format("""
                            update Order
                            set orderStatus = '%s'
                            where id = %s
                            """, orderStatus, id),
                    null);

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        Order order = currentSession
                .createQuery(String.format("from Order where id = %s", id), Order.class)
                .getSingleResult();
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findOrdersByClient(int id) {
        Transaction transaction = null;
        List<Order> orders = new ArrayList<>();

        try {
            transaction = currentSession.beginTransaction();
            User user = currentSession.createQuery(String.format("""
                    from User where id = %s
                    """, id), User.class).getSingleResult();
            orders = user.getOrders();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return orders;
    }

    @Override
    public List<Order> findAllOrders() {
        return currentSession.createQuery("from Order", Order.class).list();
    }
}
