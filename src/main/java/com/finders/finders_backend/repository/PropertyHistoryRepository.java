package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.PropertyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyHistoryRepository extends JpaRepository<PropertyHistory, Long> {

    // Gets the full timeline for one property, in order
    List<PropertyHistory> findByPropertyIdOrderByOccurredAtAsc(Long propertyId);
}