package org.example.dao;

import org.example.entity.Order;
import org.example.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    boolean saveOrder(int client_id, Order order, List<Integer> productsId);

    void updateStatusForOrder(int id, OrderStatus orderStatus);

    Optional<Order> findOrderById(int id);

    List<Order> findOrdersByClient(int id);

    List<Order> findAllOrders();
}