package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.kurisinis.HelloApplication;
import org.kurisinis.consoleCourseWork.utils.FxUtils;
import org.kurisinis.hibernateControl.CustomHibernate;
import org.kurisinis.hibernateControl.GenericHibernate;
import org.kurisinis.model.User;

import java.io.IOException;

public class LoginForm {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coursework");
/*
    public void validateAndLoad() {
        User user = new User(loginField.getText(), passwordField.getText());
        System.out.println(loginField.getText()
        + " " + passwordField.getText());
    }*/

    public void registerNewUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void validateAndLoad() throws IOException { //neveikia, reikia sutvarky validation
        CustomHibernate customHibernate = new CustomHibernate(entityManagerFactory);
        User user = customHibernate.getUserByCredentials(loginField.getText(), passwordField.getText());
        if(user != null){
            System.out.println("RAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!11");
            FXMLLoader fxmlLoader = new FXMLLoader (HelloApplication.class.getResource("/org/example/kurisinis/main-form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else{
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Oh no", "User Login Failed", "No such user exists.");
        }



    }

}
