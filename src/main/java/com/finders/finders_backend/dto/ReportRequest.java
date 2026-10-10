package com.finders.finders_backend.dto;

import com.finders.finders_backend.model.Report;

// What the frontend actually sends when someone files a report -
// just IDs and text, not full Property/User objects.
public class ReportRequest {
    private Long propertyId;      // nullable - report might be agent-only
    private Long agentId;         // nullable - report might be property-only
    private Long reportedById;    // required - who's filing it
    private Report.ReportReason reason;
    private String details;

    // Getters and setters
    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public Long getAgentId() { return agentId; }
    public void setAgentId(Long agentId) { this.agentId = agentId; }

    public Long getReportedById() { return reportedById; }
    public void setReportedById(Long reportedById) { this.reportedById = reportedById; }

    public Report.ReportReason getReason() { return reason; }
    public void setReason(Report.ReportReason reason) { this.reason = reason; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}