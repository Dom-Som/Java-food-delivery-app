package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Cuisine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> orders;
    @ManyToOne
    private Restaurant restaurant;
    private String ingredients;
    @Enumerated(EnumType.STRING)
    private List<Allergens> allergens = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private PortionSize portionSize;
    private double price;
    private boolean spicy;
    private boolean vegan;
    private LocalDateTime dateCreated;



    public Cuisine(String name, Restaurant restaurant, String ingredients, List<Allergens> allergens, PortionSize portionSize, double price, boolean spicy, boolean vegan) {
        this.name = name;
        this.restaurant = restaurant;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.portionSize = portionSize;
        this.price = price;
        this.spicy = spicy;
        this.vegan = vegan;
        this.dateCreated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", restaurant=" + (restaurant) ;
    }
}
