package org.kurisinis.hibernateControl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import javafx.scene.control.Alert;
import org.kurisinis.consoleCourseWork.utils.FxUtils;

import java.util.ArrayList;
import java.util.List;

public class GenericHibernate {
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

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
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Oh no", "DB error", "Something went wrong on insert.");

        }finally {
            if(entityManager != null) entityManager.close();
        }

    }

    public <T> void update(T entity) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            FxUtils.generateAlert(Alert.AlertType.ERROR, "DB erro", "Update failed", "Entity not found in DB.");

        }finally {
            if(entityManager != null) entityManager.close();
        }

    }
    public <T> void delete(Class<T> entityClass, int id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            T entity = entityManager.find(entityClass, id);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            FxUtils.generateAlert(Alert.AlertType.ERROR, "DB error", "Deletion failed", "idk");

        }finally {
            if(entityManager != null) entityManager.close();
        }

    }

    public <T> List<T> getAllRecords(Class<T> entityClass) {
        List<T> list = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaQuery query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(entityClass));
            Query q = entityManager.createQuery(query);
            list = q.getResultList();

        }catch(Exception e) {
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Oh no", "DB error", "Something went wrong on deletion.");
        }finally {
            if(entityManager != null) entityManager.close();
        }
        return list;
    }
    public <T> T getEntityById(Class<T> entityClass, int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

}
