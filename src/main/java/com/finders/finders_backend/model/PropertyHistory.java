package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_history")
@Getter
@Setter
@NoArgsConstructor
public class PropertyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // this is the property's full timeline over time.
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    // Stored as plain text so one column works for any kind of change -
    // e.g. previousValue "1800000", newValue "2000000" for a price change.
    private String previousValue;
    private String newValue;

    private LocalDateTime occurredAt = LocalDateTime.now();

    public enum EventType {
        LISTED, PRICE_CHANGED, AGENT_CHANGED, STATUS_CHANGED, REPORTED, VERIFIED
    }
}