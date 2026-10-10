package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nullable - a document might belong to a property (title deed)
    // rather than an agent.  
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    // Also nullable - a document might belong to an agent (CAC cert)
    // rather than a specific property.
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    // always know who actually uploaded the file, regardless
    // of what it's attached to.
    @ManyToOne
    @JoinColumn(name = "uploaded_by_id", nullable = false)
    private User uploadedBy;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    //A URL/path for where the file is.
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    public enum DocumentType {
        PROPERTY_TITLE, TENANCY_AGREEMENT, AGENT_ID, CAC_CERTIFICATE, UTILITY_BILL, OTHER
    }

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }
}