package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    //@ManyToMany
    @Transient
    private List<FoodOrder> orders;
    protected String ingredients;
    protected String allergens;
    protected String portionSize;
    protected double price;

    public Cuisine(int id, String ingredients, String allergens, String portionSize, double price) {
        this.id = id;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.portionSize = portionSize;
        this.price = price;
    }
}
