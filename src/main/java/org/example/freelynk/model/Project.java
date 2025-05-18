package org.example.freelynk.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double budget; // e.g., 100.0 (USD)

    @ElementCollection
    private List<String> requiredSkills; // e.g., ["Graphic Design", "Photoshop"]

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // Only clients can create projects

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Bid> bids;

    private LocalDateTime deadline;
}