package org.example.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDAO {
    protected final Session currentSession;

    public BaseDAO(SessionFactory sessionFactory) {
        currentSession = sessionFactory.openSession();
    }
}
