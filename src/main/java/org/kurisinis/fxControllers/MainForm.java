package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.List;
import java.util.ResourceBundle;


public class MainForm implements Initializable {
    @FXML //laikinas
    public Tab altTab;
    @FXML
    public ListView<User> userListField;
    @FXML
    public TabPane tabsPane;
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

    //<editor-fold desc="Order tab elements">
    @FXML
    public Tab foodOrderTab;
    @FXML
    public ListView<FoodOrder> orderList;
    @FXML
    public ComboBox<BasicUser> clientList;
    @FXML
    public TextField titleField;
    @FXML
    public TextField priceField;
    @FXML
    public ComboBox<Restaurant> restaurantField;
    @FXML
    public ComboBox<OrderStatus> orderStatusField;
    @FXML
    public ComboBox<OrderStatus> filterStatus;
    @FXML
    public ComboBox<BasicUser> filterClients;
    @FXML
    public DatePicker filterFrom;
    @FXML
    public DatePicker filterTo;
    @FXML
    public ListView<Cuisine> foodList;

    //</editor-fold>

    //<editor-fold desc="Cuisine tab elements">
    @FXML
    public Tab foodTab;
    @FXML
    public ListView<Cuisine> cuisineList;
    @FXML
    public TextField titleCuisineField;
    @FXML
    public ListView<Restaurant> restaurantList;
    @FXML
    public TextArea ingredientsField;
    @FXML
    public TextField cuisinePriceField;
    @FXML
    public CheckBox isSpicy;
    @FXML
    public CheckBox isVegan;
    @FXML
    public ListView<Allergens> allergenList;
    @FXML
    public ComboBox<PortionSize> portionSizeList;

    //</editor-fold>

    //<editor-fold desc="Chat tab elements">
    @FXML
    public Tab chatTab;
    public ListView<Chat> allChats;
    public ListView<Review> chatMessages;
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


        allergenList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        allergenList.getItems().addAll(Allergens.values());

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
        } else if(foodOrderTab.isSelected()) {
            clearAllOrderFields();
            List<FoodOrder> foodOrders = getFoodOrders();
            orderList.getItems().addAll(foodOrders);
            clientList.getItems().addAll(customHibernate.getOnlyBasicUsers());
            restaurantField.getItems().addAll(customHibernate.getAllRecords(Restaurant.class));
            orderStatusField.getItems().addAll(OrderStatus.values());
            cuisineList.getItems().addAll(customHibernate.getAllRecords(Cuisine.class));
            filterStatus.getItems().addAll(OrderStatus.values());
            filterClients.getItems().addAll(customHibernate.getOnlyBasicUsers());
        }else if (foodTab.isSelected()) {
            clearAllCuisineFields();
            List<Cuisine> cuisines = customHibernate.getAllRecords(Cuisine.class);
            cuisineList.getItems().addAll(cuisines);
            portionSizeList.getItems().addAll(PortionSize.values());
            allergenList.getItems().addAll(Allergens.values());
            restaurantList.getItems().addAll(customHibernate.getAllRecords(Restaurant.class));

        } else if (chatTab.isSelected()) {
            allChats.getItems().clear();
            allChats.getItems().addAll(customHibernate.getAllRecords(Chat.class));

        }
    }

    private void clearAllOrderFields(){
        orderList.getItems().clear();
        clientList.getItems().clear();
        restaurantField.getItems().clear();
        titleField.clear();
        priceField.clear();
        orderStatusField.getItems().clear();
        filterStatus.getItems().clear();
        filterClients.getItems().clear();
    }

    private void clearAllCuisineFields(){
        cuisineList.getItems().clear();
        cuisinePriceField.clear();
        titleCuisineField.clear();
        ingredientsField.clear();
        portionSizeList.getItems().clear();
        allergenList.getItems().clear();
        isVegan.setSelected(false);
        isSpicy.setSelected(false);
        restaurantList.getItems().clear();

    }

    //<editor-fold desc="User tab functionality">
    public void loadUser() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/user-form.fxml"));
        Parent parent = fxmlLoader.load();
        try{
            UserTableParameters selectedUserParams = userTable.getSelectionModel().getSelectedItem();
            if (selectedUserParams == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Update error!", "No user selected!", "Please make sure you have selected the user you want to update");
                return;
            }
            User selectedUser = customHibernate.getEntityById(User.class, selectedUserParams.getId());

            UserForm userForm = fxmlLoader.getController();
            userForm.setData(entityManagerFactory, selectedUser, true);
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Update error!", "An unexpected error occurred",e.getMessage());
        }


        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        setData(entityManagerFactory, currentUser);
    }

    public void addUser() throws IOException {
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
            if (selectedParams == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "No user selected!", "Please make sure you have selected the user you want to delete");
                return;
            }
            boolean confirmed = FxUtils.confirmationWindow("Confirm Deletion", "Are you sure you want to delete this user?", "This action cannot be undone!");

            if (confirmed) {
                User selectedUser = customHibernate.getEntityById(User.class, selectedParams.getId());
                customHibernate.delete(User.class, selectedUser.getId());
                reloadTableData();
            }
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "An unexpected error occurred",e.getMessage());
        }
    }

    public void loadChatFrom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/chat-form.fxml"));
        Parent parent = fxmlLoader.load();

        try{
            FoodOrder currentOrder = orderList.getSelectionModel().getSelectedItem();
            if (currentOrder == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Selection error!", "No food order selected!", "Please make sure you have the correct order selected");
                return;
            }

            ChatForm chatForm = fxmlLoader.getController();
            chatForm.setData(entityManagerFactory, currentUser, currentOrder);
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Update error!", "An unexpected error occurred",e.getMessage());
        }

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    //</editor-fold>

    //<editor-fold desc="Food order tab functionality">
    private List<FoodOrder> getFoodOrders() {
        if(currentUser instanceof Restaurant) {
            return customHibernate.getRestaurantOrders((Restaurant) currentUser);
        }else{
            return customHibernate.getAllRecords(FoodOrder.class);
        }

    }

    public static boolean isNumeric(String str) {
        try{
            Double.parseDouble(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void deleteOrder() {
        FoodOrder selectedOrder = orderList.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "No food order selected!", "Please make sure you have selected the food order you want to delete");
            return;
        }

        boolean confirmed = FxUtils.confirmationWindow("Confirm Deletion", "Are you sure you want to delete this food order?", "This action cannot be undone!");

        if (confirmed) {
            customHibernate.delete(FoodOrder.class, selectedOrder.getId());
            fillOrderList();
        }
    }

    public void createOrder() {
        if(isNumeric(priceField.getText())) {
            FoodOrder foodOrder = new FoodOrder(titleField.getText(), Double.parseDouble(priceField.getText()), clientList.getValue(),foodList.getSelectionModel().getSelectedItems(), restaurantField.getValue());
            if (foodOrder.getRestaurant() == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Creation error!", "No restaurant is selected!", "Please make sure you have selected a restaurant if you want to create a food order");
                return;
            }

            customHibernate.create(foodOrder);


            fillOrderList();
        }else{
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Oh no!", "Wrong price format!", "Please enter a valid price!");
        }

    }

    public void updateOrder() {
        FoodOrder foodOrder = orderList.getSelectionModel().getSelectedItem();
        if (foodOrder == null) {
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Update error!", "No food order selected!", "Please make sure you have selected the food order you want to update");
            return;
        }
        foodOrder.setRestaurant(restaurantField.getSelectionModel().getSelectedItem());
        foodOrder.setName(titleField.getText());
        foodOrder.setPrice(Double.parseDouble(priceField.getText()));
        foodOrder.setOrderStatus(orderStatusField.getValue());
        foodOrder.setCustomer(clientList.getSelectionModel().getSelectedItem());
        foodOrder.setDateUpdated(LocalDateTime.now());
        foodOrder.setItems(foodList.getSelectionModel().getSelectedItems());

        customHibernate.update(foodOrder);
        fillOrderList();
    }

    private void fillOrderList(){
        orderList.getItems().clear();
        orderList.getItems().addAll(customHibernate.getAllRecords(FoodOrder.class));
    }

    public void loadOrderInfo() {
        FoodOrder selectedFoodOrder = orderList.getSelectionModel().getSelectedItem();
        clientList.getItems().stream()
                .filter(c -> c.getId() == selectedFoodOrder.getCustomer().getId())
                .findFirst()
                .ifPresent(u->clientList.getSelectionModel().select(u));
        titleField.setText(selectedFoodOrder.getName());
        priceField.setText(String.valueOf(selectedFoodOrder.getPrice()));
        restaurantField.getItems().stream()
                .filter(c -> c.getId() == selectedFoodOrder.getRestaurant().getId())
                .findFirst()
                .ifPresent(u->restaurantField.getSelectionModel().select(u));
        orderList.getItems().stream()
                .filter(c -> c.getId() == selectedFoodOrder.getId())
                .findFirst()
                .ifPresent(u->orderStatusField.getSelectionModel().select(u.getOrderStatus()));
        foodList.getItems().stream()
                .filter(c -> c.getId() == selectedFoodOrder.getId())
                .findFirst()
                .ifPresent(u->foodList.getSelectionModel().select(u));
    }
    private void disableFoodOrderFields(){
        if(orderStatusField.getSelectionModel().getSelectedItem() == OrderStatus.COMPLETED){
            clientList.setDisable(true);
            priceField.setDisable(true);
        }
    }


    public void loadRestaurantMenuForOrder() {
        foodList.getItems().clear();
        foodList.getItems().addAll(customHibernate.getRestaurantCuisine(restaurantField.getSelectionModel().getSelectedItem()));
    }

    public void filterOrders() {
        OrderStatus status = filterStatus.getValue();
        BasicUser client = filterClients.getValue();
        LocalDate start = filterFrom.getValue();
        LocalDate end = filterTo.getValue();
        Restaurant restaurant = restaurantField.getValue();

        List<FoodOrder> results = customHibernate.getFilteredOrders(
                status,
                client,
                start,
                end,
                restaurant
        );

        orderList.getItems().clear();
        orderList.getItems().addAll(results);
    }



    //</editor-fold>

    //<editor-fold desc="Cuisine tab functionality">

    private void fillCuisineList(){
        cuisineList.getItems().clear();
        cuisineList.getItems().addAll(customHibernate.getAllRecords(Cuisine.class));
    }
    public void createNewMenuItem() {
        if(isNumeric(cuisinePriceField.getText())) {
            Cuisine cuisine = new Cuisine(titleCuisineField.getText(), restaurantList.getSelectionModel().getSelectedItem(), ingredientsField.getText(), allergenList.getSelectionModel().getSelectedItems(), portionSizeList.getValue(), Double.parseDouble(cuisinePriceField.getText()), isSpicy.isSelected(), isVegan.isSelected());
            String error = validateCuisineInput();
            if (error != null) {
                FxUtils.generateAlert(
                        Alert.AlertType.WARNING,
                        "Invalid input",
                        "Cannot create menu item",
                        error
                );
                return;
            }
            if (cuisine.getRestaurant() == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Creation error!", "No restaurant is selected!", "Please make sure you have selected a restaurant if you want to create a menu item");
                return;
            }

            customHibernate.create(cuisine);
        }else{
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Oh no!", "Wrong price format!", "Please enter a valid price!");
        }
        fillCuisineList();
    }

    public void updateMenuItem() {
        Cuisine currentCuisine = cuisineList.getSelectionModel().getSelectedItem();
        if (currentCuisine == null) {
            FxUtils.generateAlert(
                    Alert.AlertType.WARNING,
                    "Update error!",
                    "No food item selected!",
                    "Select the item you want to update."
            );
            return;
        }
        currentCuisine.setRestaurant(restaurantList.getSelectionModel().getSelectedItem());
        currentCuisine.setName(titleCuisineField.getText());
        currentCuisine.setPrice(Double.parseDouble(cuisinePriceField.getText()));
        currentCuisine.setIngredients(ingredientsField.getText());
        currentCuisine.setAllergens(allergenList.getSelectionModel().getSelectedItems());
        currentCuisine.setPortionSize(portionSizeList.getValue());
        currentCuisine.setVegan(isVegan.isSelected());
        currentCuisine.setSpicy(isSpicy.isSelected());

        customHibernate.update(currentCuisine);
        fillCuisineList();
    }

    public void deleteMenuItem() {
        Cuisine selectedItem = cuisineList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "No food item selected!", "Please make sure you have selected the food item you want to delete");
            return;
        }

        boolean confirmed = FxUtils.confirmationWindow("Confirm Deletion", "Are you sure you want to delete this food item?", "This action cannot be undone!");

        if (confirmed) {
            customHibernate.delete(Cuisine.class, selectedItem.getId());
            fillCuisineList();
        }
    }

    public void loadRestaurantMenu() {
        cuisineList.getItems().clear();
        cuisineList.getItems().addAll(customHibernate.getRestaurantCuisine(restaurantList.getSelectionModel().getSelectedItem()));
    }

    public void loadCuisineInfo() {
        Cuisine selectedCuisine = cuisineList.getSelectionModel().getSelectedItem();

        titleCuisineField.setText(selectedCuisine.getName());
        cuisinePriceField.setText(String.valueOf(selectedCuisine.getPrice()));
        ingredientsField.setText(selectedCuisine.getIngredients());
        allergenList.getSelectionModel().clearSelection();
        for (Allergens allergen : selectedCuisine.getAllergens()) {
            allergenList.getSelectionModel().select(allergen);
        }
        portionSizeList.setValue(selectedCuisine.getPortionSize());
        isVegan.setSelected(selectedCuisine.isVegan());
        isSpicy.setSelected(selectedCuisine.isSpicy());
        restaurantList.getItems().stream()
                .filter(c -> c.getId() == selectedCuisine.getRestaurant().getId())
                .findFirst()
                .ifPresent(u->restaurantList.getSelectionModel().select(u));
    }

    private String validateCuisineInput() {
        if (titleCuisineField.getText() == null || titleCuisineField.getText().isBlank()) {
            return "Cuisine name cannot be empty.";
        }

        if (restaurantList.getSelectionModel().getSelectedItem() == null) {
            return "A restaurant must be selected.";
        }

        if (!isNumeric(cuisinePriceField.getText())) {
            return "Price must be a valid number.";
        }

        if (ingredientsField.getText() == null || ingredientsField.getText().isBlank()) {
            return "Ingredients cannot be empty.";
        }

        if (portionSizeList.getValue() == null) {
            return "Please select a portion size.";
        }

        return null;
    }
    //</editor-fold>


    //<editor-fold desc="Chat tab functionality">
    public void loadChatMessages() {
        Chat selectedChat = allChats.getSelectionModel().getSelectedItem();
        if (selectedChat == null) {
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Selection error!", "No chat selected!", "Please select a chat first.");
            return;
        }
        Chat managedChat = customHibernate.getEntityById(Chat.class, selectedChat.getId());

        chatMessages.getItems().clear();
        chatMessages.getItems().addAll(managedChat.getMessages());
    }

    public void deleteMessage() {
        Review selectedItem = chatMessages.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Deletion error!", "No message selected!", "Please make sure you have selected the message you want to delete");
            return;
        }

        boolean confirmed = FxUtils.confirmationWindow("Confirm Deletion", "Are you sure you want to delete this message?", "This action cannot be undone!");
        if (!confirmed) return;

        selectedItem.setChat(null);
        selectedItem.setFeedbackSender(null);
        selectedItem.setReviewOwner(null);

        customHibernate.update(selectedItem);
        customHibernate.delete(Review.class, selectedItem.getId());

        loadChatMessages();
    }

    public void loadChatForAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/kurisinis/chat-form.fxml"));
        Parent parent = fxmlLoader.load();

        try{
            FoodOrder selectedOrder = allChats.getSelectionModel().getSelectedItem().getFoodOrder();
            if (selectedOrder == null) {
                FxUtils.generateAlert(Alert.AlertType.WARNING, "Selection error!", "No chat selected!", "Please make sure you have the correct chat selected");
                return;
            }

            ChatForm chatForm = fxmlLoader.getController();
            chatForm.setData(entityManagerFactory, currentUser, selectedOrder);
        }catch(Exception e){
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Update error!", "An unexpected error occurred",e.getMessage());
        }

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        loadChatMessages();
    }
    //</editor-fold>

}