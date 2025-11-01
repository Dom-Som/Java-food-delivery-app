package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kurisinis.hibernateControl.GenericHibernate;
import org.kurisinis.model.BasicUser;
import org.kurisinis.model.User;

public class UserForm {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public TextField addressField;
    @FXML
    public RadioButton userRadio;
    @FXML
    public RadioButton restaurantRadio;
    @FXML
    public RadioButton clientRadio;
    @FXML
    public RadioButton driverRadio;

    private EntityManagerFactory entityManagerFactory ;
    private GenericHibernate genericHibernate;

    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.genericHibernate = new GenericHibernate(entityManagerFactory);
    }


    public void createNewUser() {
        if (userRadio.isSelected()) {
            User user = new User(loginField.getText(),
                    passwordField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    phoneNumberField.getText()); //isAdmin true nustatyti reikia
            genericHibernate.create(user);

        }else if (clientRadio.isSelected()) {
            BasicUser basicUser = new BasicUser();
            passwordField.getText();
            nameField.getText();
            surnameField.getText();
            phoneNumberField.getText();
            addressField.getText();
            genericHibernate.create(basicUser);
        }
    }
    public void disableFields() {
        if(userRadio.isSelected()) {
            //addressField.setDisable(true);
            addressField.setVisible(false);
            //enable user fields, disable other fields
        }else if(restaurantRadio.isSelected()) {
            addressField.setDisable(false);
        }else if(clientRadio.isSelected()) {

        }else{

        }
    }
}
