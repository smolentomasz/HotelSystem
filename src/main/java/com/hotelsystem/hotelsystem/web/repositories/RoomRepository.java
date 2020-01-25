package com.hotelsystem.hotelsystem.web.repositories;

import com.hotelsystem.hotelsystem.web.data_models.Room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
