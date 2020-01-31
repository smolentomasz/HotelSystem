package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.Hotel;
import com.hotelsystem.hotelsystem.web.data_models.ServerResponse;
import com.hotelsystem.hotelsystem.web.repositories.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/")
    public String home() {
        return "Home page";
    }
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> allHotels(){
        return ResponseEntity.ok().body(hotelRepository.findAll());
    }
    @GetMapping("/hotels/{name}")
    public ResponseEntity<?> findHotel(@PathVariable("name") String hotelName){
        Hotel getHotel;
        if(hotelRepository.findById(hotelName).isPresent()){
            getHotel = hotelRepository.findById(hotelName).get();
            return ResponseEntity.ok().body(getHotel);
        }
        else{
            return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "Hotel with name " + hotelName + " not found!", "/hotels/{name}"), HttpStatus.NOT_FOUND);
        }
    }
}
