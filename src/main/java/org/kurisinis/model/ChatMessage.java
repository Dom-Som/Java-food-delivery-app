package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne
    protected BasicUser sender;
    @ManyToOne
    protected BasicUser feedbackSender;
    @ManyToOne
    protected Chat chat;
    protected String message;
    protected LocalDateTime timestamp;
    protected boolean isRead;

}
