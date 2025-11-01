package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.kurisinis.HelloApplication;
import org.kurisinis.model.User;

import java.io.IOException;

public class LoginForm {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coursework");

    public void validateAndLoad() {
        User user = new User(loginField.getText(), passwordField.getText());
        System.out.println(loginField.getText()
        + " " + passwordField.getText());
    }

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

}
