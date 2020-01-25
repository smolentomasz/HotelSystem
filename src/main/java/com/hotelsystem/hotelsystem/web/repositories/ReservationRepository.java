package com.hotelsystem.hotelsystem.web.repositories;

import com.hotelsystem.hotelsystem.web.data_models.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAll();
    Reservation findBy(Long id);
}
