package org.example.freelynk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "freelancers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Freelancer extends User {
    @Column(name = "profile_image")
    private String profileImage;

    @Column
    private String description;

    @Column(name = "years_of_exp")
    private Integer yearsOfExp;

    @Column
    private String location;

    @Column
    private String languages;

    @Column
    private String occupation;

    @ElementCollection
    @Column(name = "skills")
    private List<String> skills;

    @Column
    private String phone;

    @Column
    private Double rating;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Bid> bids;



}