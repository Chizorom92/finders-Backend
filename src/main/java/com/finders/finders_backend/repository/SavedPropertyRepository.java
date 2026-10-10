package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.SavedProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long> {

    // Gets everything a given user has saved it powers the
    // Saved Properties dashboard page.
    List<SavedProperty> findByUserId(Long userId);

    // Check if a specific user already saved a specific property -
    Optional<SavedProperty> findByUserIdAndPropertyId(Long userId, Long propertyId);
}