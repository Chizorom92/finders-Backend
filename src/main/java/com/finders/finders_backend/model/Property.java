package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Marks this class as a database table called "properties"
@Entity
@Table(name = "properties")
@Getter          // auto-generates getX() methods
@Setter          // auto-generates setX() methods
@NoArgsConstructor // empty constructor, required by JPA
public class Property {

    // Primary key, auto-incremented by MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000) // longer text field for descriptions
    private String description;

    private String address;

    @Enumerated(EnumType.STRING) // stores enum as readable text, not a number
    private PropertyType propertyType;

    private Integer bedrooms;
    private Integer bathrooms;

    // Fee breakdown - each cost is its own field, nothing hidden
    private Double annualRent;
    private Double agencyFee;
    private Double cautionFee;
    private Double legalFee;
    private Double inspectionFee;

    // Calculated later in code (sum of rent + fees), not entered directly
    private Double totalUpfrontCost;

    // Starts as HIGH risk until verified
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel = RiskLevel.HIGH;

    // Starts as ACTIVE when first created
    @Enumerated(EnumType.STRING)
    private PropertyStatus status = PropertyStatus.ACTIVE;

    public enum PropertyType {
        APARTMENT, DUPLEX, BUNGALOW, SELF_CONTAIN, OFFICE, LAND, OTHER
    }

    public enum RiskLevel {
        LOW, MEDIUM, HIGH
    }

    public enum PropertyStatus {
        ACTIVE, RENTED, REMOVED, UNDER_REVIEW
    }
}