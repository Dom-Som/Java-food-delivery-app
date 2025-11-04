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
    @OneToMany(mappedBy = "restaurant",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuisine> dishes;
    private String workHours;
    private double rating;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> orders ;


    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin, String workHours) {
        super(login, password, name, surname, phoneNumber, address, isAdmin);
        this.workHours = workHours;
    }

}
