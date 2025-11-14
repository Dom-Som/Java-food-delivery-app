package org.kurisinis.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.kurisinis.consoleCourseWork.utils.FxUtils;
import org.kurisinis.hibernateControl.CustomHibernate;
import org.kurisinis.model.*;

public class ChatForm {
    @FXML
    public ListView<Review> messageList;
    @FXML
    public TextArea messageBody;

    private EntityManagerFactory entityManagerFactory;
    private CustomHibernate customHibernate;
    private User currentUser;
    private FoodOrder currentFoodOrder;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, FoodOrder order) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = currentUser;
        this.customHibernate = new CustomHibernate(entityManagerFactory);

        this.currentFoodOrder = customHibernate.getEntityById(FoodOrder.class, order.getId());

        loadMessages();
    }


    public void sendMessage() {

        currentFoodOrder = customHibernate.getEntityById(FoodOrder.class, currentFoodOrder.getId());

        Chat chat = currentFoodOrder.getChat();

        if (chat == null) {
            chat = new Chat("Chat for " + currentFoodOrder.getName(), currentFoodOrder);
            currentFoodOrder.setChat(chat);

            customHibernate.update(currentFoodOrder); // attaches chat

            currentFoodOrder = customHibernate.getEntityById(FoodOrder.class, currentFoodOrder.getId());
            chat = currentFoodOrder.getChat();
        }

        chat = customHibernate.getEntityById(Chat.class, chat.getId());

        Review message = new Review(messageBody.getText(), currentUser, chat);
        customHibernate.create(message);

        messageList.getItems().add(message);
        messageBody.clear();
    }




    public void loadMessages() {
        currentFoodOrder = customHibernate.getEntityById(FoodOrder.class, currentFoodOrder.getId());

        if (currentFoodOrder.getChat() != null) {
            messageList.getItems().setAll(currentFoodOrder.getChat().getMessages());
        }
    }
}
