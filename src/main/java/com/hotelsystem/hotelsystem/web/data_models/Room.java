package com.hotelsystem.hotelsystem.web.data_models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Room {
    @Id
    private long room_number;
    private long slot_number;
    private double price_per_day;

    @OneToOne
    private Hotel hotel;

    @OneToOne
    private RoomInfo roomInfo;

    @ManyToOne
    private Reservation reservation;

    public Room(long room_number, long slot_number, double price_per_day){
        this.room_number = room_number;
        this.slot_number = slot_number;
        this.price_per_day = price_per_day;
    }

    public Room() {
    }

    public long getRoom_number() {
        return room_number;
    }

    public long getSlot_number() {
        return slot_number;
    }

    public double getPrice_per_day() {
        return price_per_day;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }
}
