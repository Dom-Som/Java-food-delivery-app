package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class BasicUser extends User{
    protected String address;
    @Transient
    protected List<FoodOrder> myOrders;
    @Transient
    protected List<Review> myReviews;
    @Transient
    protected List<Review> feedback;

    public BasicUser(String login, String password, String name, String surname, String phoneNumber, String address) {
        super(login, password, name, surname, phoneNumber);
        this.address = address;
        this.myOrders = new ArrayList<>();
        this.myReviews = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }
}
