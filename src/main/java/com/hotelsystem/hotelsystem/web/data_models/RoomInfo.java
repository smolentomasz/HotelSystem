package com.hotelsystem.hotelsystem.web.data_models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long info_id;
    private long floor_number;
    private long bathroom_number;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomInfo")
    private Set<Room> rooms = new HashSet<>();


    public RoomInfo(long floor_number, long bathroom_number){
        this.floor_number = floor_number;
        this.bathroom_number = bathroom_number;
    }

    public RoomInfo(){

    }

    public long getInfo_id() {
        return info_id;
    }

    public long getFloor_number() {
        return floor_number;
    }

    public long getBathroom_number() {
        return bathroom_number;
    }
}
