package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Review;
import com.finders.finders_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/reviews
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // GET /api/reviews -> returns all reviews as JSON
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}