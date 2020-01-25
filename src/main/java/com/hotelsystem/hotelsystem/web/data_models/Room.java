package com.hotelsystem.hotelsystem.web.data_models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {
    @Id
    private long room_number;
    private long slot_number;
    private double price_per_day;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private RoomInfo roomInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Set<Reservation> reservation = new HashSet<>();

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

    public void setRoom_number(long room_number) {
        this.room_number = room_number;
    }

    public void setSlot_number(long slot_number) {
        this.slot_number = slot_number;
    }

    public void setPrice_per_day(double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }
}
