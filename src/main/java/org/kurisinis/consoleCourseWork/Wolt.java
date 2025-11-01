package org.kurisinis.consoleCourseWork;

import lombok.Getter;
import lombok.Setter;
import org.kurisinis.model.FoodOrder;
import org.kurisinis.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor

public class Wolt {
    private List<User> allSystemUsers;
    private List<FoodOrder>  allFoodOrders;

    public Wolt() {
        this.allSystemUsers = new ArrayList<>();
        this.allFoodOrders = new ArrayList<>();
    }
}
