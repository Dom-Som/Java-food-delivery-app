package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static jakarta.persistence.FetchType.LAZY;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class BasicUser extends User{
    protected String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Chat> chats;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<FoodOrder> myOrders;
    @OneToMany(mappedBy = "sender",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<ChatMessage> myReviews;
    @OneToMany(mappedBy = "feedbackSender",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<ChatMessage> feedback;

    public BasicUser(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin) {
        super(login, password, name, surname, phoneNumber, isAdmin);
        this.address = address;
        this.myOrders = new ArrayList<>();
        this.myReviews = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }
}
