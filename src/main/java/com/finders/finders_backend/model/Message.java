package com.finders.finders_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // lets a message thread reference which listing it's about,
    // without forcing every message to be tied to a property.
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(length = 2000, nullable = false)
    private String content;

    private LocalDateTime sentAt = LocalDateTime.now();

    // Nullable Set this when the receiver opens it.
    private LocalDateTime readAt;
}