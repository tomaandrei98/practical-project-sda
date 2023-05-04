package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.dao.impl.UserDAOImpl;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.service.base.BaseService;
import org.example.validator.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl(getSessionFactory());
    }

    @Override
    public boolean saveUser(User user) {
        return userDAO.saveUser(user);
    }

    @Override
    public Optional<User> deleteUser(User user) {
        return userDAO.deleteUser(user);
    }

    @Override
    public void updatePasswordForUser(int id, String password) {
        User tempUser = userDAO.findUserById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("user with id = %s could not be found", id)));

        userDAO.updatePasswordForUser(id, password);
    }

    @Override
    public void updateAddressForUser(int userId, String city, String country, String street, int zipCode) {
        userDAO.updateAddressForUser(userId, city, country, street, zipCode);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userDAO.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public double getTotalSumForUserById(int id) {
        return userDAO.findUserById(id).orElseThrow()
                .getOrders().stream()
                .map(Order::getProducts)
                .map(products -> products.stream().map(Product::getPrice).reduce(0.0, Double::sum))
                .reduce(0.0, Double::sum);
    }
}