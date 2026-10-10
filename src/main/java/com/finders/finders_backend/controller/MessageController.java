package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Message;
import com.finders.finders_backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/messages
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // GET /api/messages -> returns all messages as JSON
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}