package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.Reservation;
import com.hotelsystem.hotelsystem.web.data_models.Room;
import com.hotelsystem.hotelsystem.web.data_models.User;
import com.hotelsystem.hotelsystem.web.repositories.ReservationRepository;
import com.hotelsystem.hotelsystem.web.repositories.RoomRepository;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok().body(reservationRepository.findAll());
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody Map<String, String> body) throws ParseException {
        String pesel = body.get("client_id");
        Long room_id = Long.parseLong(body.get("room_id"));
        String start_date = body.get("start_date");
        String end_date = body.get("end_date");

        User getUser;
        Room getRoom;
        Reservation newReservation;
        if (pesel.isEmpty() || room_id == null || start_date.isEmpty() || end_date.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (userRepository.findById(pesel).isPresent()) {
                if (roomRepository.findById(room_id).isPresent()) {
                    getRoom = roomRepository.findById(room_id).get();
                    getUser = userRepository.findById(pesel).get();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date startReservation = format.parse(start_date);
                    Date endReservation = format.parse(end_date);
                    long reservationTime = ((endReservation.getTime() - startReservation.getTime()) / (1000 * 60 * 60 * 24));

                    double price_per_day = getRoom.getPrice_per_day();
                    double summaryPrice = reservationTime * price_per_day;
                    newReservation = new Reservation(start_date, end_date, summaryPrice, getUser, getRoom);
                    return ResponseEntity.ok().body(reservationRepository.save(newReservation));
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<List<Reservation>> getAllUserReservation(@PathVariable("id") String pesel) {
        if (userRepository.findById(pesel).isPresent()) {
            return ResponseEntity.ok().body(reservationRepository.findAll());
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long reservation_id, @RequestBody Map<String, String> body) throws ParseException {
        String pesel = body.get("client_id");
        Long room_id = Long.parseLong(body.get("room_id"));
        String start_date = body.get("start_date");
        String end_date = body.get("end_date");

        Room getRoom;
        Reservation getReservation;
        if (pesel.isEmpty() || room_id == null || start_date.isEmpty() || end_date.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (reservationRepository.findById(reservation_id).isPresent()) {
                getReservation = reservationRepository.findById(reservation_id).get();
                if (getReservation.getUser().getPesel().equals(pesel)) {
                    if (userRepository.findById(pesel).isPresent()) {
                        if (roomRepository.findById(room_id).isPresent()) {
                            getRoom = roomRepository.findById(room_id).get();
                            getRoom.setRoom_number(room_id);
                            getReservation.setStart_date(start_date);
                            getReservation.setEnd_date(end_date);
                            getReservation.setRoom(getRoom);

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date startReservation = format.parse(start_date);
                            Date endReservation = format.parse(end_date);
                            long reservationTime = ((endReservation.getTime() - startReservation.getTime()) / (1000 * 60 * 60 * 24));

                            double price_per_day = getRoom.getPrice_per_day();
                            double summaryPrice = reservationTime * price_per_day;

                            getReservation.setPrice(summaryPrice);
                            return ResponseEntity.ok().body(reservationRepository.save(getReservation));
                        } else {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }

                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long reservation_id, @RequestBody Map<String, String> body) {
        String pesel = body.get("client_id");
        Reservation getReservation;
        if (pesel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (reservationRepository.findById(reservation_id).isPresent()) {
                getReservation = reservationRepository.findById(reservation_id).get();
                if (getReservation.getUser().getPesel().equals(pesel)) {
                    reservationRepository.delete(getReservation);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
