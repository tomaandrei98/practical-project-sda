package org.example.dao;

import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    boolean saveUser(User user);

    void updatePasswordForUser(int id, String password);

    void updateAddressForUser(int userId, String city, String country, String street, int zipCode);

    Optional<User> deleteUser(User user);

    Optional<User> findUserById(int id);

    List<User> findAllUsers();
}
