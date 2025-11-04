package org.kurisinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Driver extends BasicUser{
    private String licence;
    private LocalDate bDate;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private boolean isAvailable;
    private double rating;
    private int totalDeliveries;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chats;
    @OneToMany(mappedBy = "driver",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> myOrders;

    public Driver(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin, String licence, LocalDate bDate, VehicleType vehicleType, boolean isAvailable, double rating, int totalDeliveries, String vehicleInfo) {
        super(login, password, name, surname, phoneNumber, address, isAdmin);
        this.licence = licence;
        this.bDate = bDate;
        this.vehicleType = vehicleType;
        this.isAvailable = isAvailable;
        this.rating = rating;
        this.totalDeliveries = totalDeliveries;
    }

    public Driver(String login, String password, String name, String surname, String phoneNumber, String address, boolean isAdmin, String licence, LocalDate bDate, VehicleType vehicleType) {
        super(login, password, name, surname, phoneNumber, address, isAdmin);
        this.licence = licence;
        this.bDate = bDate;
        this.vehicleType = vehicleType;
    }

    public Driver(String login, String password, String name, String surname, String phoneNumber, String address, String licence, LocalDate bDate, VehicleType vehicleType) {
        super(login, password, name, surname, phoneNumber, address);
        this.licence = licence;
        this.bDate = bDate;
        this.vehicleType = vehicleType;
    }
}

