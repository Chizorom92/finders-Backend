package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    // Gets all documents attached to a given property
    List<Document> findByPropertyId(Long propertyId);

    // Get all documents attached to a given agent
    List<Document> findByAgentId(Long agentId);
}