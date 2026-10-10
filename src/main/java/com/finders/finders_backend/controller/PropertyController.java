package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Property;
import com.finders.finders_backend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/properties
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    // GET /api/properties -> returns all properties as JSON
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // GET /api/properties/search?location=Yaba&minPrice=500000&bedrooms=2
    // Every parameter is optional - the frontend only sends the filters
    // the user actually set on the Search Properties page.
    @GetMapping("/search")
    public List<Property> searchProperties(
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "propertyType", required = false) Property.PropertyType propertyType,
            @RequestParam(name = "bedrooms", required = false) Integer bedrooms,
            @RequestParam(name = "bathrooms", required = false) Integer bathrooms,
            @RequestParam(name = "riskLevel", required = false) Property.RiskLevel riskLevel) {

        return propertyRepository.searchProperties(
                location, minPrice, maxPrice, propertyType, bedrooms, bathrooms, riskLevel);
    }
}