package com.hotelsystem.hotelsystem.web.repositories;

import com.hotelsystem.hotelsystem.web.data_models.Reservation;
import com.hotelsystem.hotelsystem.web.data_models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);
}
