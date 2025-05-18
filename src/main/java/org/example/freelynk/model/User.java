package org.example.freelynk.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // CLIENT or FREELANCER

    private String location; // e.g., "Morocco"
    private Integer experienceYears; // e.g., "5 years"
    private String languages; // e.g., "English"

    // Freelancer-specific fields (nullable for clients)
    private String bio;

    // Common fields for both roles
    private String displayName;
    private String profilePictureUrl;



    // Omitted getters/setters for brevity
}

