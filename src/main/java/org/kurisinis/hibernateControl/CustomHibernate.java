package org.kurisinis.hibernateControl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Alert;
import org.kurisinis.consoleCourseWork.utils.FxUtils;
import org.kurisinis.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomHibernate extends GenericHibernate{
    public CustomHibernate(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public User getUserByCredentials(String login, String password) {
        User user = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);

            query.select(root).where(cb.and(
                    cb.equal(root.get("login"), login),
                    cb.equal(root.get("password"), password)
            ));
            Query q = entityManager.createQuery(query);
            user = (User) q.getSingleResult();
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Oh no", "User Login Failed", "No such user or wrong credentials!");
        }finally{
            if(entityManager!=null)entityManager.close();
        }
        return user;
    }

    public <T> T getEntityById(Class<T> entityClass, int id){
        T entity = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entity = entityManager.find(entityClass, id);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Waring!", "Something went wrong during search for specific element", "Could not get item ID");
        }finally{
            if(entityManager!=null)entityManager.close();
        }
        return entity;
    }

    public List<FoodOrder> getRestaurantOrders (Restaurant restaurant) {
        List<FoodOrder> orders = new ArrayList<>();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<FoodOrder> query = cb.createQuery(FoodOrder.class);
            Root<FoodOrder> root = query.from(FoodOrder.class);

            query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            Query q = entityManager.createQuery(query);
            orders = (List<FoodOrder>) q.getResultList();
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Waring!", "Something went wrong during search for specific restaurant", e.getMessage());
        }
        return orders;
    }

    public List<FoodOrder> getFilteredOrders (OrderStatus orderStatus, BasicUser client, LocalDate start, LocalDate end, Restaurant restaurant) {
        List<FoodOrder> orders = new ArrayList<>();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<FoodOrder> query = cb.createQuery(FoodOrder.class);
            Root<FoodOrder> root = query.from(FoodOrder.class);

            //predikatai
            if(restaurant != null){
                query.select(root).where(cb.equal(root.get("restaurant"), restaurant));

            }else{
                query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            }
            Query q = entityManager.createQuery(query);
            orders = (List<FoodOrder>) q.getSingleResult();
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Waring!", "Something went wrong during search for specific restaurant", e.getMessage());
        }
        return orders;
    }

    public List<Cuisine> getRestaurantCuisine (Restaurant restaurant) {
        List<Cuisine> menu = new ArrayList<>();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Cuisine> query = cb.createQuery(Cuisine.class);
            Root<Cuisine> root = query.from(Cuisine.class);

            query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            Query q = entityManager.createQuery(query);
            menu = q.getResultList();
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Waring!", "Something went wrong during search for specific restaurant", e.getMessage());
        }
        return menu;
    }
    public List<BasicUser> getOnlyBasicUsers(){
        List<BasicUser> basicUsers = new ArrayList<>();
        for(BasicUser user : getAllRecords(BasicUser.class)){
            if(!(user instanceof Restaurant) && !(user instanceof Driver)){
                basicUsers.add(user);
            }
        }
        return basicUsers;
    }
}
