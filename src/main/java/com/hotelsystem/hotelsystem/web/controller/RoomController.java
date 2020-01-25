package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.Room;
import com.hotelsystem.hotelsystem.web.repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/room")
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok().body(roomRepository.findAll());
    }
}
