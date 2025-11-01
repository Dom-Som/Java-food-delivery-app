package org.kurisinis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Review {
    protected int id;
    protected int rate;
    protected String text;

    public Review(int id, int rate, String text) {
        this.id = id;
        this.rate = rate;
        this.text = text;
    }

}
