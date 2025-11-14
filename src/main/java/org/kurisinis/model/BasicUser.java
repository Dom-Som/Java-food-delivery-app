package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class BasicUser extends User{
    protected String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> myOrders;
    @OneToMany(mappedBy = "reviewOwner",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> myReviews;
    @OneToMany(mappedBy = "feedbackSender",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> feedback;

    public BasicUser(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin) {
        super(login, password, name, surname, phoneNumber, isAdmin);
        this.address = address;
        this.myOrders = new ArrayList<>();
        this.myReviews = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }

    public BasicUser(String login, String password, String name, String surname, String phoneNumber, String address) {
        super(login, password, name, surname, phoneNumber);
        this.address = address;
    }
}
