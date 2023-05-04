package org.example.service;

import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean saveUser(User user);

    Optional<User> deleteUser(User user);

    void updatePasswordForUser(int id, String password);

    void updateAddressForUser(int userId, String city, String country, String street, int zipCode);

    Optional<User> findUserById(int id);

    List<User> findAllUsers();

    double getTotalSumForUserById(int id);
}
