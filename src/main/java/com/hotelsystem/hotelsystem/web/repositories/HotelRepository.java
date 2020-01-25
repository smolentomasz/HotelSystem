package com.hotelsystem.hotelsystem.web.repositories;

import com.hotelsystem.hotelsystem.web.data_models.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    Hotel findByName(String name);
}
