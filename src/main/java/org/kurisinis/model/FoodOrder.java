package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class FoodOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToMany
    private List<Cuisine> items;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Chat chat;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public FoodOrder (String name, double price, BasicUser customer, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public FoodOrder(String name, double price, BasicUser customer, Restaurant restaurant, OrderStatus orderStatus) {
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderStatus = orderStatus;
    }

    public FoodOrder(String name, double price, BasicUser customer, List<Cuisine> items, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.items = items;
        this.restaurant = restaurant;
        this.orderStatus = OrderStatus.PENDING;
        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return name + " " + price + " " + customer + " " + restaurant + " " + orderStatus + " " + dateCreated;
    }
}
