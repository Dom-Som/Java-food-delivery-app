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
    private String name;
    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @OneToOne(mappedBy ="chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FoodOrder order;
    @OneToMany(mappedBy = "chat",  cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<ChatMessage> messages;
    private LocalDateTime createdAt;
    private LocalDateTime lastMessageAt;
    private boolean isActive;
}
