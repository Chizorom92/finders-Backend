package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Area;
import com.finders.finders_backend.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/areas
@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    // GET /api/areas -> returns all areas as JSON
    @GetMapping
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }
}