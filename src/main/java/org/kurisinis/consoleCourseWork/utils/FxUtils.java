package org.kurisinis.consoleCourseWork.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;


//https://code.makery.ch/blog/javafx-dialogs-official/
public class FxUtils {
    public static void generateAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
