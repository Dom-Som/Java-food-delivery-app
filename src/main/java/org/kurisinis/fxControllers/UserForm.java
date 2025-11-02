package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.kurisinis.HelloApplication;
import org.kurisinis.hibernateControl.GenericHibernate;
import org.kurisinis.model.BasicUser;
import org.kurisinis.model.Driver;
import org.kurisinis.model.Restaurant;
import org.kurisinis.model.User;

import java.io.IOException;

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
                    phoneNumberField.getText(),
                    true);
            genericHibernate.create(user);

        }else if (clientRadio.isSelected()) {
            BasicUser basicUser = new BasicUser(
            loginField.getText(),
            passwordField.getText(),
            nameField.getText(),
            surnameField.getText(),
            phoneNumberField.getText(),
            addressField.getText(),
                    false);
            genericHibernate.create(basicUser);
        }else if (driverRadio.isSelected()) { //finish with specific fields
            Driver driver = new Driver(
                    loginField.getText(),
                    passwordField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    phoneNumberField.getText(),
                    addressField.getText(),
                    false);
            genericHibernate.create(driver);
        }else {
            Restaurant restaurant = new Restaurant( //finish with specific fields
                    loginField.getText(),
                    passwordField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    phoneNumberField.getText(),
                    addressField.getText(),
                    false);
            genericHibernate.create(restaurant);
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

    public void returnToLoginForm(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/login-form.fxml"));
        Parent parent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(parent));
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
