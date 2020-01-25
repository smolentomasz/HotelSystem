package com.hotelsystem.hotelsystem.web.data_models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Hotel {
    @Id
    private String name;
    private String city;
    private String country;
    private long stars;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private Set<Room> rooms = new HashSet<>();

    public Hotel(String name, String city, String country, long stars){
        this.name = name;
        this.city = city;
        this.country = country;
        this.stars = stars;
    }
    public Hotel(){

    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public long getStars() {
        return stars;
    }
}
