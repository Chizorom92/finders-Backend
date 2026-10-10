package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "areas")
@Getter
@Setter
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "Yaba", "Lekki Phase 1" - each area name must be unique
    @Column(unique = true, nullable = false)
    private String name;

    // this runs the average of total upfront cost for ACTIVE listings in this
    // area. Recalculated by the service layer whenever a listing changes -
    // this is what the risk/overpriced flagging compares against.
    private Double averageAnnualRent = 0.0;

    private Integer listingCount = 0;
}