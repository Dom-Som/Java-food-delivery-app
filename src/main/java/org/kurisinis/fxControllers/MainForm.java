package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kurisinis.HelloApplication;
import org.kurisinis.hibernateControl.CustomHibernate;
import org.kurisinis.model.FoodOrder;
import org.kurisinis.model.Restaurant;
import org.kurisinis.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    @FXML
    public Tab managementTab;
    @FXML
    public Tab userTab;
    @FXML
    public Tab foodTab;
    @FXML //laikinas
    public Tab altTab;
    @FXML
    public ListView<User> userListField;
    @FXML
    public TabPane tabsPane;
    @FXML
    public Button updateButton;

    private EntityManagerFactory entityManagerFactory;
    private CustomHibernate customHibernate;
    private User currentUser;


    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = user;
        this.customHibernate = new CustomHibernate(entityManagerFactory);
        setUserFormVisibility();
    }

    private void setUserFormVisibility() {
        if(currentUser instanceof User) {
            //
        } else if (currentUser instanceof Restaurant) {
            //altTab.setDisable(true);
            tabsPane.getTabs().remove(altTab);
        }
    }

    public void reloadTableData(Event event) {
        if(userTab.isSelected()) {

        } else if (altTab.isSelected()) {
            List<User> userList = customHibernate.getAllRecords(User.class);
            userListField.getItems().addAll(userList);
         //   System.out.println("Users found: " + userList.size());
          //  for (User u : userList) System.out.println(u);


        } else if (foodTab.isSelected()) {

        }else if(managementTab.isSelected()) {
            List<FoodOrder> foodList = getFoodOrders();
        }
    }

    private List<FoodOrder> getFoodOrders() {
        if(currentUser instanceof Restaurant) {
            return customHibernate.getRestaurantOrders((Restaurant) currentUser);
        }else{
            return customHibernate.getAllRecords(FoodOrder.class);
        }

    }

    //<editor-fold desc="Alternative tab functions">
    public void openUserForm() throws IOException {

    }

    public void loadUser(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();

        User selectedUser = userListField.getSelectionModel().getSelectedItem();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory, selectedUser, true);

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void addUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();

        User selectedUser = userListField.getSelectionModel().getSelectedItem();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory, null, false);

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void deleteUser() {
        User selectedUser = userListField.getSelectionModel().getSelectedItem();
       // customHibernate.delete(User.class, selectedUser.getId());
    }
    //</editor-fold>
}
