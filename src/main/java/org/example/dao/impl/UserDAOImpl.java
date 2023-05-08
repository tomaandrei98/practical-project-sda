package org.example.dao.impl;

import org.example.dao.UserDAO;
import org.example.dao.base.BaseDAO;
import org.example.entity.User;
import org.example.validator.Validator;
import org.example.validator.exception.IllegalDeleteUserException;
import org.example.validator.impl.DeleteUserValidator;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends BaseDAO implements UserDAO {
    private final Validator<User> deleteUserValidator = new DeleteUserValidator();

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean saveUser(User user) {
        boolean success = false;
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();
            currentSession.persist(user);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return success;
    }

    @Override
    public void updatePasswordForUser(int id, String password) {
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();

            Query<Object> query = currentSession.createQuery(
                    String.format("update User set password = '%s' where id = %s", password, id),
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
    public void updateAddressForUser(int userId, String city, String country, String street, int zipCode) {
        User tempUser = Optional.ofNullable(currentSession.get(User.class, userId)).orElseThrow();
        Transaction transaction = null;

        try {
            transaction = currentSession.beginTransaction();

            Query<Object> query = currentSession.createQuery(
                    String.format("""
                            update Address set country = '%s', city = '%s', street = '%s', zipCode = %s where id = %s
                            """, country, city, street, zipCode, tempUser.getAddress().getId()),
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
    public Optional<User> deleteUser(User user) {
        Transaction transaction = null;

        try {
            deleteUserValidator.validate(user);
            try {
                transaction = currentSession.beginTransaction();
                currentSession.remove(user);
                transaction.commit();
                return Optional.ofNullable(user);
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (IllegalDeleteUserException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(currentSession.get(User.class, id));
    }

    @Override
    public List<User> findAllUsers() {
        return currentSession.createQuery("from User", User.class).list();
    }
}
