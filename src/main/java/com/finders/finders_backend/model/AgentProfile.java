package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agent_profiles")
@Getter
@Setter
@NoArgsConstructor
public class AgentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne links this AgentProfile to exactly one User.
    // @JoinColumn(unique = true) makes sure one User can't have two agentProfiles
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String businessName;


    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    private Double averageRating = 0.0;
    private Integer reviewCount = 0;
    private Integer reportCount = 0;

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }
}