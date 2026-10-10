package com.finders.finders_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    // @JsonIgnore stops this field from ever being included when Spring
    // converts a User to JSON for an API response. Without this, the
    // GET endpoint would leak password hashes to anyone who calls it.
    @JsonIgnore
    private String passwordHash;

    private String phoneNumber;

    // Same pattern as before - stored as readable text (TENANT/AGENT/ADMIN)
    @Enumerated(EnumType.STRING)
    private Role role = Role.TENANT;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        TENANT, AGENT, ADMIN
    }
}