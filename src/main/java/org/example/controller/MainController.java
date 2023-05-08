package org.example.controller;

import org.example.entity.*;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.example.service.impl.OrderServiceImpl;
import org.example.service.impl.ProductServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.validator.exception.OrderNotFoundException;
import org.example.validator.exception.ProductNotFoundException;
import org.example.validator.exception.UserNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static UserService userService = new UserServiceImpl();
    private static ProductService productService = new ProductServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();

    public static void main(String[] args) {
//        addUser();
//        System.out.println(getUserById(1));
//        updateUser(1, "newPass3");
//        System.out.println(getUserById(1));
//        deleteUser(getUserById(3));
//        System.out.println(getUserById(2));
//        updateAddressForUserWithId(1, "x", "y", "abc", 1000);
//        findAllUsers().forEach(System.out::println);

//        addProduct();
//        updateProduct(1, 3, "name3upd", "desc3upd", 50);
//        System.out.println(getProductById(2));
//        deleteProduct(1, getProductById(2));
//        System.out.println(getProductByTitle("title2"));
//        productService.findAllProducts().forEach(System.out::println);
//        System.out.println(getProductByType(ProductType.SPORT));
//        getProductsWithPriceBetween(25, 1200).forEach(System.out::println);

//        addOrder();
//        updateOrder(2, OrderStatus.SUCCESS);
//        System.out.println(getOrderById(1));
//        getOrdersByClient(2).forEach(System.out::println);
//        orderService.findAllOrders().forEach(System.out::println);
//        System.out.println(getTotalPriceByOrderId(1));
//        System.out.println(userService.getTotalSumForUserById(1));
    }

    private static void deleteUser(User user) {
        userService.deleteUser(user);
    }

    private static void updateUser(int id, String password) {
        userService.updatePasswordForUser(id, password);
    }

    private static User getUserById(int id) {
        return userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("no user with id = %s", id)));
    }

    private static void addUser() {
        Address address1 = new Address("country1", "city1", "street1", 123);
        User user1 = new User("username1", "password1", Role.ADMIN, address1);
        userService.saveUser(user1);

        Address address2 = new Address("country2", "city2", "street2", 456);
        User user2 = new User("username2", "password2", Role.REGULAR_USER, address2);
        userService.saveUser(user2);

        Address address3 = new Address("country3", "city3", "street3", 789);
        User user3 = new User("username3", "password3", Role.REGULAR_USER, address3);
        userService.saveUser(user3);
    }

    private static void updateAddressForUserWithId(int userId, String city, String country, String street, int zipCode) {
        userService.updateAddressForUser(userId, city, country, street, zipCode);
    }

    private static List<User> findAllUsers() {
        return userService.findAllUsers();
    }


    private static void addProduct() {
        Product product1 = new Product("title1", "desc1", 20, ProductType.FASHION);
        productService.saveProduct(1, product1);
        Product product2 = new Product("title2", "desc2", 40, ProductType.SPORT);
        productService.saveProduct(1, product2);
        Product product3 = new Product("title3", "desc3", 60, ProductType.FASHION);
        productService.saveProduct(1, product3);
        Product product4 = new Product("title4", "desc4", 100, ProductType.AUTO);
        productService.saveProduct(1, product4);
        Product product5 = new Product("title5", "desc5", 100, ProductType.AUTO);
        productService.saveProduct(2, product5);
    }

    private static Product getProductById(int id) {
        return productService.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("no product with id %s", id)));
    }

    private static Product getProductByTitle(String title) {
        return productService.findProductByTitle(title)
                .orElseThrow(() -> new ProductNotFoundException(String.format("no product with title %s", title)));
    }

    private static List<Product> getProductByType(ProductType productType) {
        return productService.findProductsByType(productType);
    }

    private static void deleteProduct(int userId, Product product) {
        productService.deleteProduct(userId, product);
    }

    private static void updateProduct(int userId, int id, String name, String description, double price) {
        productService.updateProduct(userId, id, name, description, price);
    }

    private static List<Product> getProductsWithPriceBetween(double min, double max) {
        return productService.findProductsWithPriceBetween(min, max);
    }


    private static void addOrder() {
        Order order1 = new Order(
                "username1",
                "address1",
                LocalDate.of(2022, Month.APRIL, 10),
                OrderStatus.PENDING);

        List<Integer> productsId1 = new ArrayList<>();
        productsId1.add(1);
        productsId1.add(1);
        productsId1.add(2);
        orderService.saveOrder(1, order1, productsId1);

        Order order2 = new Order(
                "username2",
                "address2",
                LocalDate.now(),
                OrderStatus.PENDING);

        List<Integer> productsId2 = new ArrayList<>();
        productsId2.add(1);
        productsId2.add(2);
        productsId2.add(3);
        orderService.saveOrder(2, order2, productsId2);

        Order order3 = new Order(
                "username3",
                "address3",
                LocalDate.now(),
                OrderStatus.PENDING);

        List<Integer> productsId3 = new ArrayList<>();
        productsId3.add(1);
        productsId3.add(3);
        productsId3.add(3);
        orderService.saveOrder(3, order3, productsId3);
    }

    private static void updateOrder(int orderId, OrderStatus orderStatus) {
        orderService.updateStatusForOrder(orderId, orderStatus);
    }

    private static Order getOrderById(int id) {
        return orderService.findOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format("not found order with id = %s", id)));
    }

    private static List<Order> getOrdersByClient(int id) {
        return orderService.findOrdersByClient(id);
    }

    private static double getTotalPriceByOrderId(int id) {
        return orderService.getTotalPriceByOrderId(id);
    }
}