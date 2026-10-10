package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Document;
import com.finders.finders_backend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/documents
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    // GET /api/documents -> returns all documents as JSON
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}