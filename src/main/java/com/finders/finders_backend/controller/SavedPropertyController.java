package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.SavedProperty;
import com.finders.finders_backend.repository.SavedPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/saved-properties
@RestController
@RequestMapping("/api/saved-properties")
public class SavedPropertyController {

    @Autowired
    private SavedPropertyRepository savedPropertyRepository;

    // GET /api/saved-properties -> returns all saved-property records as JSON
    @GetMapping
    public List<SavedProperty> getAllSavedProperties() {
        return savedPropertyRepository.findAll();
    }
}