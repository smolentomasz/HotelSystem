package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.AuthenticationResponse;
import com.hotelsystem.hotelsystem.web.data_models.User;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;
import com.hotelsystem.hotelsystem.web.security.HotelSystemUserDetailService;
import com.hotelsystem.hotelsystem.web.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HotelSystemUserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestHeader("Login") String login, @RequestHeader("Password") String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        }catch(BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(login);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

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
