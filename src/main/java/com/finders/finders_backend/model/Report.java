package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nullable - a report might target a specific listing
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    // or an agent in general, without a specific property.
    // Enforce "at least one of property/agent is set" in your service layer.
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    // to always know who filed the report.
    @ManyToOne
    @JoinColumn(name = "reported_by_id", nullable = false)
    private User reportedBy;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    @Column(length = 1000)
    private String details;

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.OPEN;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum ReportReason {
        DUPLICATE_LISTING, FAKE_LISTING, AGENT_UNRESPONSIVE, FEES_NOT_AS_DESCRIBED, SCAM, OTHER
    }

    public enum ReportStatus {
        OPEN, REVIEWED, DISMISSED
    }
}