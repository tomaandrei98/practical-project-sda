package org.example.service.base;

import org.example.entity.Address;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BaseService {
    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Order.class)
                    .buildSessionFactory();
        }

        return sessionFactory;
    }
}
