package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kurisinis.HelloApplication;
import org.kurisinis.consoleCourseWork.utils.FxUtils;
import org.kurisinis.hibernateControl.CustomHibernate;
import org.kurisinis.model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainForm implements Initializable {
    @FXML
    public Tab managementTab;
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
    //<editor-fold desc="User tab elements">
    @FXML
    public Tab userTab;
    @FXML
    public TableView<UserTableParameters> userTable;
    @FXML
    public TableColumn<UserTableParameters, Integer> idCol;
    @FXML
    public TableColumn<UserTableParameters, String> userTypeCol;
    @FXML
    public TableColumn<UserTableParameters, String> loginCol;
    @FXML
    public TableColumn<UserTableParameters, String> passwordCol;
    @FXML
    public TableColumn<UserTableParameters, String> nameCol;
    @FXML
    public TableColumn<UserTableParameters, String> surnameCol;
    @FXML
    public TableColumn<UserTableParameters, String> phoneCol;
    @FXML
    public TableColumn<UserTableParameters, String> addressCol;
    @FXML
    public TableColumn<UserTableParameters, String> workHoursCol;
    @FXML
    public TableColumn<UserTableParameters, String> licenseCol;
    @FXML
    public TableColumn<UserTableParameters, String> bdateCol;
    @FXML
    public TableColumn<UserTableParameters, String> vehicleCol;
    @FXML
    public TableColumn dummyCol;
    //</editor-fold>
    private ObservableList<UserTableParameters> userObservableList = FXCollections.observableArrayList();
    private EntityManagerFactory entityManagerFactory;
    private CustomHibernate customHibernate;
    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //<editor-fold desc="User management table initialize">
        userTable.setEditable(true);
        userTable.setItems(userObservableList);//
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setPassword(event.getNewValue());
            customHibernate.update(user);
        });

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setName(event.getNewValue());
            customHibernate.update(user);
        });
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSurname(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setSurname(event.getNewValue());
            customHibernate.update(user);
        });
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPhoneNumber(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setPhoneNumber(event.getNewValue());
            customHibernate.update(user);
        });
       addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setAddress(event.getNewValue());
            BasicUser user = customHibernate.getEntityById(BasicUser.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setAddress(event.getNewValue());
            customHibernate.update(user);
        });
        workHoursCol.setCellValueFactory(new PropertyValueFactory<>("workHours"));
        workHoursCol.setCellFactory(TextFieldTableCell.forTableColumn());
        workHoursCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setWorkHours(event.getNewValue());
            Restaurant user = customHibernate.getEntityById(Restaurant.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setWorkHours(event.getNewValue());
            customHibernate.update(user);
        });
        licenseCol.setCellValueFactory(new PropertyValueFactory<>("license"));
        licenseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        licenseCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLicense(event.getNewValue());
            Driver user = customHibernate.getEntityById(Driver.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setLicence(event.getNewValue());
            customHibernate.update(user);
        });
        bdateCol.setCellValueFactory(new PropertyValueFactory<>("bdate"));
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        //</editor-fold>
    }

    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = user;
        this.customHibernate = new CustomHibernate(entityManagerFactory);

        reloadTableData();
        setUserFormVisibility();
    }

    private void setUserFormVisibility() {
    if (currentUser instanceof Restaurant || currentUser instanceof BasicUser || currentUser instanceof Driver) {
            tabsPane.getTabs().remove(altTab);
            tabsPane.getTabs().remove(userTab);
        }else{
        tabsPane.getTabs().remove(altTab);
    }
    }

    public void reloadTableData() {
        if(userTab.isSelected()) {
            List<User> users = customHibernate.getAllRecords(User.class);
            userObservableList.clear();
            for(User u:users) {
                UserTableParameters userTableParameters = new UserTableParameters();
                userTableParameters.setId(u.getId());
                userTableParameters.setUserType(u.getClass().getSimpleName());
                userTableParameters.setLogin(u.getLogin());
                userTableParameters.setPassword(u.getPassword());

                userTableParameters.setName(u.getName());
                userTableParameters.setSurname(u.getSurname());
                userTableParameters.setPhoneNumber(u.getPhoneNumber());
                if(u instanceof BasicUser) {
                    userTableParameters.setAddress(((BasicUser) u).getAddress());
                }
                if(u instanceof Restaurant) {
                    userTableParameters.setWorkHours(((Restaurant) u).getWorkHours());
                }
                if(u instanceof Driver) {
                    userTableParameters.setLicense(((Driver) u).getLicence());
                    userTableParameters.setBdate(String.valueOf(((Driver) u).getBDate()));
                    userTableParameters.setVehicleType(String.valueOf(((Driver) u).getVehicleType()));
                }
                userObservableList.add(userTableParameters);
            }
            userTable.setItems(userObservableList);

        } else if (altTab.isSelected()) {
            List<User> userList = customHibernate.getAllRecords(User.class);
            userListField.getItems().addAll(userList);
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
    public void openUserForm() throws IOException {

    }

    public void loadUser(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();
        UserTableParameters selectedUserParams = userTable.getSelectionModel().getSelectedItem();
        User selectedUser = customHibernate.getEntityById(User.class, selectedUserParams.getId());

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory, selectedUser, true);

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        setData(entityManagerFactory, currentUser);
    }

    public void addUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory, null, false);

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        setData(entityManagerFactory, currentUser);
    }

    public void deleteUser() {
        try{
            UserTableParameters selectedParams = userTable.getSelectionModel().getSelectedItem();
            boolean confirmed = FxUtils.confirmationWindow("Confirm Deletion", "Are you sure you want to delete this user?", "This action cannot be undone!");

            if (confirmed) {
                User selectedUser = customHibernate.getEntityById(User.class, selectedParams.getId());
                customHibernate.delete(User.class, selectedUser.getId());
                reloadTableData();
            }
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "You have no user selected for deletion!","Please make sure you have selected the user you want to delete");
        }
    }
}
