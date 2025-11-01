package org.kurisinis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Restaurant extends BasicUser{
    protected List<Cuisine> dishes;
    protected String workHours;
    protected double rating;

    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, List<Cuisine> dishes, String workHours, double rating) {
        super(login, password, name, surname, phoneNumber, address);
        this.dishes = dishes;
        this.workHours = workHours;
        this.rating = rating;
    }
}
