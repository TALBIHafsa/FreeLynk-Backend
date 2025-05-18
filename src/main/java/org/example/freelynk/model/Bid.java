package org.example.freelynk.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private Double bidAmount; // e.g., 50.0 (USD)
    private Integer deliveryDays; // e.g., 7 days
    private BidStatus status; // PENDING, ACCEPTED, REJECTED
}


