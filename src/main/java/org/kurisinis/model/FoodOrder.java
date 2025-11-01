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
@AllArgsConstructor
@Entity

public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Restaurant restaurant;
    //@ManyToMany(mappedBy = "order", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @Transient
    protected List<Cuisine> items;
    protected double price;
    @OneToOne
    private Chat chat;

}
