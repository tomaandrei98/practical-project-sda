package org.example.service;

import org.example.entity.Order;
import org.example.entity.OrderStatus;
import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean saveOrder(int client_id, Order order, List<Integer> productsId);

    void updateStatusForOrder(int id, OrderStatus orderStatus);

    Optional<Order> findOrderById(int id);

    List<Order> findOrdersByClient(int id);

    List<Order> findAllOrders();

    double getTotalPriceByOrderId(int id);
}
