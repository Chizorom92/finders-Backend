package com.finders.finders_backend.controller;

import com.finders.finders_backend.dto.ReportRequest;
import com.finders.finders_backend.model.Property;
import com.finders.finders_backend.model.Report;
import com.finders.finders_backend.model.User;
import com.finders.finders_backend.repository.PropertyRepository;
import com.finders.finders_backend.repository.ReportRepository;
import com.finders.finders_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    // GET /api/reports -> all reports (admin view)
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // GET /api/reports/open -> only OPEN reports, for an admin's
    // "pending reports" dashboard list
    @GetMapping("/open")
    public List<Report> getOpenReports() {
        return reportRepository.findByStatus(Report.ReportStatus.OPEN);
    }

    // POST /api/reports -> a tenant files a new report
    @PostMapping
    public Report createReport(@RequestBody ReportRequest request) {

        // Enforce "at least one of property/agent is set" here in code,
        // since the database allows both to be null.
        if (request.getPropertyId() == null && request.getAgentId() == null) {
            throw new IllegalArgumentException("A report must target a property, an agent, or both.");
        }

        Report report = new Report();

        if (request.getPropertyId() != null) {
            Property property = propertyRepository.findById(request.getPropertyId())
                    .orElseThrow(() -> new IllegalArgumentException("Property not found"));
            report.setProperty(property);
        }

        if (request.getAgentId() != null) {
            User agent = userRepository.findById(request.getAgentId())
                    .orElseThrow(() -> new IllegalArgumentException("Agent not found"));
            report.setAgent(agent);
        }

        User reportedBy = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new IllegalArgumentException("Reporting user not found"));
        report.setReportedBy(reportedBy);

        report.setReason(request.getReason());
        report.setDetails(request.getDetails());
        // status defaults to OPEN automatically (set in the Report entity)

        return reportRepository.save(report);
    }

    // PUT /api/reports/{id}/status?status=REVIEWED
    // Lets an admin resolve a report once they've looked into it.
    @PutMapping("/{id}/status")
    public Report updateReportStatus(
            @PathVariable Long id,
            @RequestParam Report.ReportStatus status) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));
        report.setStatus(status);
        return reportRepository.save(report);
    }
}