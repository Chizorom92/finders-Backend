package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.AgentProfile;
import com.finders.finders_backend.repository.AgentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles requests to /api/agent-profiles
@RestController
@RequestMapping("/api/agent-profiles")
public class AgentProfileController {

    @Autowired
    private AgentProfileRepository agentProfileRepository;

    // GET /api/agent-profiles -> returns all agent profiles as JSON
    @GetMapping
    public List<AgentProfile> getAllAgentProfiles() {
        return agentProfileRepository.findAll();
    }
}