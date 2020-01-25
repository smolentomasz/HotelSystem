package com.hotelsystem.hotelsystem.web.data_models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hotel {
    @Id
    private String name;
    private String city;
    private String country;
    private long stars;

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
