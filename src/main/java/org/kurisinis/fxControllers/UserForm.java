package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kurisinis.HelloApplication;
import org.kurisinis.consoleCourseWork.utils.FxUtils;
import org.kurisinis.hibernateControl.CustomHibernate;
import org.kurisinis.hibernateControl.GenericHibernate;
import org.kurisinis.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
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
    @FXML
    public DatePicker bdateField;
    @FXML
    public TextField licenseField;
    @FXML
    public ComboBox<VehicleType> vehicleTypeField = new ComboBox<>();
    @FXML
    public Pane driverFields;
    @FXML
    public AnchorPane userFields;
    @FXML
    public TextField workingHoursField;
    @FXML
    public Button updateButton;

    private EntityManagerFactory entityManagerFactory ;
    private GenericHibernate genericHibernate;
    private User userForUpdate;
    private boolean isForUpdate;


    public void setData(EntityManagerFactory entityManagerFactory, User user, boolean isForUpdate) {
        this.entityManagerFactory = entityManagerFactory;
        this.genericHibernate = new GenericHibernate(entityManagerFactory);
        this.userForUpdate = user;
        this.isForUpdate = isForUpdate;
        fillUserDataForUpdate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleTypeField.getItems().addAll(VehicleType.values());
        disableFields();
    }

    private void fillUserDataForUpdate() {
        if (userForUpdate != null && isForUpdate) {
            updateButton.setVisible(true);
             if (userForUpdate instanceof Restaurant && isForUpdate) {
                restaurantRadio.setSelected(true);
                disableFields();
                loginField.setText(userForUpdate.getLogin());
                passwordField.setText(userForUpdate.getPassword());
                nameField.setText(userForUpdate.getName());
                surnameField.setText(userForUpdate.getSurname());
                phoneNumberField.setText(userForUpdate.getPhoneNumber());
                addressField.setText(((Restaurant)userForUpdate).getAddress());
                workingHoursField.setText(((Restaurant)userForUpdate).getWorkHours());
            }else if(userForUpdate instanceof Driver && isForUpdate) {
                driverRadio.setSelected(true);
                disableFields();
                loginField.setText(userForUpdate.getLogin());
                passwordField.setText(userForUpdate.getPassword());
                nameField.setText(userForUpdate.getName());
                surnameField.setText(userForUpdate.getSurname());
                phoneNumberField.setText(userForUpdate.getPhoneNumber());
                addressField.setText(((Driver)userForUpdate).getAddress());
                licenseField.setText(((Driver)userForUpdate).getLicence());
                bdateField.setValue(((Driver)userForUpdate).getBDate());
                vehicleTypeField.setValue(((Driver)userForUpdate).getVehicleType());
            }else if(userForUpdate instanceof User && isForUpdate) {
                userRadio.setSelected(true);
                disableFields();
                loginField.setText(userForUpdate.getLogin());
                passwordField.setText(userForUpdate.getPassword());
                nameField.setText(userForUpdate.getName());
                surnameField.setText(userForUpdate.getSurname());
                phoneNumberField.setText(userForUpdate.getPhoneNumber());
            }else if(userForUpdate instanceof BasicUser && isForUpdate) {
                clientRadio.setSelected(true);
                disableFields();
                loginField.setText(userForUpdate.getLogin());
                passwordField.setText(userForUpdate.getPassword());
                nameField.setText(userForUpdate.getName());
                surnameField.setText(userForUpdate.getSurname());
                phoneNumberField.setText(userForUpdate.getPhoneNumber());
                addressField.setText(((BasicUser)userForUpdate).getAddress());
            }
        } else {
            updateButton.setVisible(false);
        }
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
                    false,
                    licenseField.getText(),
                    bdateField.getValue(),
                    vehicleTypeField.getValue()
                    );
            genericHibernate.create(driver);
        }else {
            Restaurant restaurant = new Restaurant(
                    loginField.getText(),
                    passwordField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    phoneNumberField.getText(),
                    addressField.getText(),
                    false,
                    workingHoursField.getText()
                    );
            genericHibernate.create(restaurant);
        }
    }
    public void disableFields() {
        if(userRadio.isSelected()) {
            userFields.setVisible(true);
            addressField.setVisible(false);
            workingHoursField.setVisible(false);
            driverFields.setVisible(false);
        }else if(restaurantRadio.isSelected()) {
            userFields.setVisible(true);
            addressField.setVisible(true);
            workingHoursField.setVisible(true);
            driverFields.setVisible(false);
        }else if(clientRadio.isSelected()) {
            userFields.setVisible(true);
            addressField.setVisible(true);
            workingHoursField.setVisible(false);
            driverFields.setVisible(false);
        }else{
            userFields.setVisible(true);
            addressField.setVisible(true);
            workingHoursField.setVisible(false);
            driverFields.setVisible(true);
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

    public void updateUser(ActionEvent actionEvent) {
    }
}
