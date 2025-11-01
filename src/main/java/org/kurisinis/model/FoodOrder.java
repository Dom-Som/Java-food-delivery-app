package org.kurisinis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class FoodOrder {
    protected int id;
    protected List<Cuisine> items;
    protected double price;
    protected List<Chat> chat;

    public FoodOrder(List<Chat> chat, double price, List<Cuisine> items, int id) {
        this.chat = chat;
        this.price = price;
        this.items = items;
        this.id = id;
    }
}
