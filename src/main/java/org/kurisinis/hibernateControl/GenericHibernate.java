package org.kurisinis.hibernateControl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.kurisinis.model.User;

public class GenericHibernate {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public GenericHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public <T> void create(T entity) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            //klaidos atveju panaudosiu alertus

        }finally {
            if(entityManager != null) entityManager.close();
        }

    }
}
