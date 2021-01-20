package com.tehnik.dao.hibernate;

import com.tehnik.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateProductDAO {


    private final SessionFactory sessionFactory;

    public HibernateProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session currentSession() {
        return sessionFactory.openSession();
    }


    public void addProduct(Product product) {
        Transaction transaction = currentSession().beginTransaction();
        currentSession().save(product);
        currentSession().close();
        transaction.commit();
    }

    public Product getProduct(Long id){
        Product product = currentSession().get(Product.class, id);
        return product;
    }


    public List<Product> getAll() {
        return currentSession().createQuery("From Product").list();
    }
}
