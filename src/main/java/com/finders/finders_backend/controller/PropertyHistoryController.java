package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.PropertyHistory;
import com.finders.finders_backend.repository.PropertyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/property-history
@RestController
@RequestMapping("/api/property-history")
public class PropertyHistoryController {

    @Autowired
    private PropertyHistoryRepository propertyHistoryRepository;

    // GET /api/property-history -> returns all history records as JSON
    @GetMapping
    public List<PropertyHistory> getAllPropertyHistory() {
        return propertyHistoryRepository.findAll();
    }
}