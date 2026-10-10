package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_checks")
@Getter
@Setter
@NoArgsConstructor
public class VerificationCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One property gets exactly one verification check record -
    // same @OneToOne + unique pattern as AgentProfile <-> User.
    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    // Each one starts false and gets turned to true by an admin during review.
    private Boolean addressChecked = false;
    private Boolean propertyExistenceChecked = false;
    private Boolean agentVerified = false;
    private Boolean ownerAuthorizationChecked = false;
    private Boolean listingReviewed = false;

    // Which admin did the checking and when it was done can also be nullable since a new
    // property has no checks done yet.
    @ManyToOne
    @JoinColumn(name = "checked_by_admin_id")
    private User checkedByAdmin;

    private LocalDateTime checkedAt;
}