package com.hotelsystem.hotelsystem.web.data_models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservation_id;
    private String start_date;
    private String end_date;
    private double price;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<Room> room = new HashSet<>();

    public Reservation(String start_date, String end_date, double price, User user) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.user = user;
    }

    public Reservation(){

    }


}
