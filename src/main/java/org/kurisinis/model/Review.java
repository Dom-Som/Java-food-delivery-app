package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    private String text;
    private LocalDateTime dateCreated;
    @ManyToOne
    private User reviewOwner;
    @ManyToOne
    private BasicUser feedbackSender;
    @ManyToOne(cascade = CascadeType.ALL)
    private Chat chat;

    public Review(String text, User reviewOwner, Chat chat) {
        this.text = text;
        this.dateCreated = LocalDateTime.now();
        this.reviewOwner = reviewOwner;
        this.chat = chat;
    }

    @Override
    public String toString() {
        return reviewOwner + " says:\n" + text + "\n| " + dateCreated+" |";
    }
}
