package org.kurisinis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            URL fxmlLocation = HelloApplication.class.getResource("/org/example/kurisinis/login-form.fxml");

            if (fxmlLocation == null) {
                System.err.println("ERROR: FXML file not found!");
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Pain :)");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error during FXML load:");
            e.printStackTrace();
        }
    }
}
