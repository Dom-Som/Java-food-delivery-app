package org.kurisinis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
    @OneToMany
    private List<FoodOrder> orders ;

    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, List<Cuisine> dishes, String workHours, double rating) {
        super(login, password, name, surname, phoneNumber, address);
        this.dishes = dishes;
        this.workHours = workHours;
        this.rating = rating;
    }
}
