package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    // makes it possible look up an area by name instead of by id
    Optional<Area> findByName(String name);
}