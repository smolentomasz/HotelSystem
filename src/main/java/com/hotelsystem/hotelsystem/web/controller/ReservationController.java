package com.hotelsystem.hotelsystem.web.controller;

import com.hotelsystem.hotelsystem.web.data_models.Reservation;
import com.hotelsystem.hotelsystem.web.data_models.Room;
import com.hotelsystem.hotelsystem.web.data_models.ServerResponse;
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
    public ResponseEntity<?> addNewReservation(@RequestBody Map<String, String> body) throws ParseException {
        if (body.get("client_id").isEmpty() || body.get("room_id").isEmpty() || body.get("start_date").isEmpty() || body.get("end_date").isEmpty()) {
            return new ResponseEntity<>(new ServerResponse(HttpStatus.BAD_REQUEST, "Missing or invalid data", "/reservation"), HttpStatus.BAD_REQUEST);
        } else {
            String pesel = body.get("client_id");
            Long room_id = Long.parseLong(body.get("room_id"));
            String start_date = body.get("start_date");
            String end_date = body.get("end_date");

            User getUser;
            Room getRoom;
            Reservation newReservation;
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
                    return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "Room with number" + room_id + " not found", "/reservation"), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "User with username" + pesel + " not found", "/reservation"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getAllUserReservation(@PathVariable("id") String pesel) {
        if (userRepository.findById(pesel).isPresent()) {
            User getUser = userRepository.findById(pesel).get();
            return ResponseEntity.ok().body(reservationRepository.findAllByUser(getUser));
        } else
            return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "User with username" + pesel + " not found", "/reservation"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable("id") Long reservation_id, @RequestBody Map<String, String> body) throws ParseException {
        if (body.get("client_id").isEmpty() || body.get("room_id").isEmpty() || body.get("start_date").isEmpty() || body.get("end_date").isEmpty()) {
            return new ResponseEntity<>(new ServerResponse(HttpStatus.BAD_REQUEST, "Missing or invalid data", "/reservation/{id}"), HttpStatus.BAD_REQUEST);
        } else {
            String pesel = body.get("client_id");
            Long room_id = Long.parseLong(body.get("room_id"));
            String start_date = body.get("start_date");
            String end_date = body.get("end_date");

            Room getRoom;
            Reservation getReservation;
            if (reservationRepository.findById(reservation_id).isPresent()) {
                getReservation = reservationRepository.findById(reservation_id).get();

                if (userRepository.findById(pesel).isPresent()) {
                    if (getReservation.getUser().getPesel().equals(pesel)) {
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
                            return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "Room with number" + room_id + " not found", "/reservation/{id}"), HttpStatus.NOT_FOUND);
                        }

                    } else {
                        return new ResponseEntity<>(new ServerResponse(HttpStatus.FORBIDDEN, "Access denied", "/reservation/{id}"), HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "User with username" + pesel + " not found", "/reservation/{id}"), HttpStatus.NOT_FOUND);
                }
            } else
                return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "Reservation with id" + reservation_id + " not found", "/reservation/{id}"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long reservation_id, @RequestBody Map<String, String> body) {
        if (body.get("client_id").isEmpty()) {
            return new ResponseEntity<>(new ServerResponse(HttpStatus.BAD_REQUEST, "Missing or invalid data", "/reservation/{id}"), HttpStatus.BAD_REQUEST);
        } else {
            String pesel = body.get("client_id");
            Reservation getReservation;
            if (reservationRepository.findById(reservation_id).isPresent()) {
                getReservation = reservationRepository.findById(reservation_id).get();
                if (getReservation.getUser().getPesel().equals(pesel)) {
                    reservationRepository.delete(getReservation);
                    return new ResponseEntity<>(new ServerResponse(HttpStatus.NO_CONTENT, "Deleted reservation with id " + reservation_id + " succesfully", "/guestbook/{id}"), HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(new ServerResponse(HttpStatus.FORBIDDEN, "Access denied", "/reservation/{id}"), HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND, "Reservation with id" + reservation_id + " not found", "/reservation/{id}"), HttpStatus.NOT_FOUND);
            }
        }
    }
}
