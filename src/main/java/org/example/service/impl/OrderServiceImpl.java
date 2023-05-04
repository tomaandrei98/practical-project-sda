package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.dao.impl.OrderDAOImpl;
import org.example.entity.Order;
import org.example.entity.OrderStatus;
import org.example.entity.Product;
import org.example.service.OrderService;
import org.example.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl extends BaseService implements OrderService {

    private OrderDAO orderDAO;

    public OrderServiceImpl() {
        orderDAO = new OrderDAOImpl(getSessionFactory());
    }

    @Override
    public boolean saveOrder(int client_id, Order order, List<Integer> productsId) {
        return orderDAO.saveOrder(client_id, order, productsId);
    }

    @Override
    public void updateStatusForOrder(int id, OrderStatus orderStatus) {
        orderDAO.updateStatusForOrder(id, orderStatus);
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return orderDAO.findOrderById(id);
    }

    @Override
    public List<Order> findOrdersByClient(int id) {
        return orderDAO.findOrdersByClient(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDAO.findAllOrders();
    }

    @Override
    public double getTotalPriceByOrderId(int id) {
        Order order = orderDAO.findOrderById(id).orElseThrow();
        return order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
}
