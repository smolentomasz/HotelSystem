package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.Hotel;
import com.hotelsystem.hotelsystem.web.data_models.User;
import com.hotelsystem.hotelsystem.web.repositories.HotelRepository;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Hotel> findHotel(@PathVariable("name") String hotelName){
        Hotel getHotel;
        if(hotelRepository.findById(hotelName).isPresent()){
            getHotel = hotelRepository.findById(hotelName).get();
            return ResponseEntity.ok().body(getHotel);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
