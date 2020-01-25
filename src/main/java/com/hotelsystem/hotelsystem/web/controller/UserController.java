package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.User;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> addNewUser(@RequestBody Map<String, String> body){
        String pesel = body.get("pesel");
        String name = body.get("name");
        String surname = body.get("surname");
        String email = body.get("email");
        String password = body.get("password");
        String userRole = body.get("role");

        return ResponseEntity.ok().body(userRepository.save(new User(pesel, password, name, surname, email, userRole)));
    }
}
