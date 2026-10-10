package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.User;
import com.finders.finders_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/users
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Spring auto-injects a ready-to-use UserRepository here
    @Autowired
    private UserRepository userRepository;

    // GET /api/users -> returns all users as JSON
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}