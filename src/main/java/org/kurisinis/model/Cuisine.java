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
    @ManyToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> orders;
    @Enumerated(EnumType.STRING)
    protected Ingredients ingredients;
    @Enumerated(EnumType.STRING)
    protected Allergens allergens;
    @Enumerated(EnumType.STRING)
    protected PortionSize portionSize;
    protected double price;

    public Cuisine(int id, Ingredients ingredients, Allergens allergens, PortionSize portionSize, double price) {
        this.id = id;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.portionSize = portionSize;
        this.price = price;
    }
}
