package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Get all reviews left for a specific agent and used to calculate
    // their averageRating and show reviews on their profile.
    List<Review> findByAgentId(Long agentId);
}