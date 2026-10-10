package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// uniqueConstraints prevents the same user from saving the same
// property more than once - the database itself enforces this
@Entity
@Table(name = "saved_properties",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "property_id"}))
@Getter
@Setter
@NoArgsConstructor
public class SavedProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    private LocalDateTime savedAt = LocalDateTime.now();
}