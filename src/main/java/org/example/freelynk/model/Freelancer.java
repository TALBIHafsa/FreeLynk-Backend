package org.example.freelynk.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "freelancers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Freelancer extends User {
    @ElementCollection
    private List<String> skills; // e.g., ["Web Design", "JavaScript"]

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Bid> bids;

    // Additional fields (education, certifications)
    @ElementCollection
    private List<String> certifications; // e.g., ["Full-Stack Web Development Certificate"]
}
