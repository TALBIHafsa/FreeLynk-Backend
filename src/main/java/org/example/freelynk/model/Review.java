package org.example.freelynk.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    private Integer rating; // 1-5 stars
    private String comment;
    private LocalDateTime createdAt;
}
