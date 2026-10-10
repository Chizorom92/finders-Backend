package com.finders.finders_backend.controller;

import com.finders.finders_backend.model.VerificationCheck;
import com.finders.finders_backend.repository.VerificationCheckRepository;
import com.finders.finders_backend.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification-checks")
public class VerificationCheckController {

    @Autowired
    private VerificationCheckRepository verificationCheckRepository;

    @Autowired
    private VerificationService verificationService;

    @GetMapping
    public List<VerificationCheck> getAllVerificationChecks() {
        return verificationCheckRepository.findAll();
    }

    // PUT /api/verification-checks/{propertyId}
    @PutMapping("/{propertyId}")
    public VerificationCheck updateChecklist(
            @PathVariable Long propertyId,
            @RequestBody VerificationCheck updatedChecks) {
        return verificationService.updateChecklist(propertyId, updatedChecks);
    }
}