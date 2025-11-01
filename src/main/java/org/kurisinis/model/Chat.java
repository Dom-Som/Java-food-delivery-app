package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @OneToOne
    protected FoodOrder order;
    @OneToMany(mappedBy = "chat",  cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<ChatMessage> messages;
    private LocalDateTime createdAt;
    private LocalDateTime lastMessageAt;
    private boolean isActive;
/*
    public Chat(int id, BasicUser customer, Driver driver, FoodOrder order, LocalDateTime createdAt, LocalDateTime lastMessageAt, boolean isActive) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.order = order;
        this.createdAt = createdAt;
        this.lastMessageAt = lastMessageAt;
        this.isActive = isActive;
    }*/
}
