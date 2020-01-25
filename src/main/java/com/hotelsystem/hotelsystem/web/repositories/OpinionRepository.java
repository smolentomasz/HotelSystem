package com.hotelsystem.hotelsystem.web.repositories;

import com.hotelsystem.hotelsystem.web.data_models.Opinion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> getAll();

}
