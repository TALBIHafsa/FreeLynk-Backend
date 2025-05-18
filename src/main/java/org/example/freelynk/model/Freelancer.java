package org.example.freelynk.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "freelancers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Freelancer extends User {
    @ElementCollection
    private List<String> skills; 

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @ElementCollection
    private List<String> certifications; 
}
