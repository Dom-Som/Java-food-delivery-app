package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Restaurant extends BasicUser{
    @ManyToMany
    private List<Cuisine> dishes;
    private String workHours;
    private double rating;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> orders ;

    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin, List<Cuisine> dishes, String workHours, double rating) {
        super(login, password, name, surname, phoneNumber, address, isAdmin);
        this.dishes = dishes;
        this.workHours = workHours;
        this.rating = rating;
    }

    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin) {
        super(login, password, name, surname, phoneNumber, address, isAdmin);
    }
}
