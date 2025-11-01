package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Review extends ChatMessage{
    @ManyToOne
    private Restaurant restaurant;
    @OneToOne
    private FoodOrder foodOrder;
    protected String text;


}
