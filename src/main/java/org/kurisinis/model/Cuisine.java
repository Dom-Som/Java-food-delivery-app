package org.kurisinis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cuisine {
    protected int id;
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
