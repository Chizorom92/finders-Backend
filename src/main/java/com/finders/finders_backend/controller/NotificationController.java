package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.Notification;
import com.finders.finders_backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/notifications
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // GET /api/notifications -> returns all notifications as JSON
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}