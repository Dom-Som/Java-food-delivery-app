package org.kurisinis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Chat {
    protected int id;
    protected List<Review> messages;

    public Chat(int id, List<Review> messages) {
        this.id = id;
        this.messages = messages;
    }
}
