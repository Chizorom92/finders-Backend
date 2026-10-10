package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The agent being reviewed
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private User agent;

    // The tenant/user leaving the review - different relationship,
    // same User entity, same @ManyToOne pattern as Document had.
    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    private Integer rating; // 1-5, enforce range in validation later

    @Column(length = 1000)
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();
}