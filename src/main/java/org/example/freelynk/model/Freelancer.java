package org.example.freelynk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
<<<<<<< HEAD
    private List<String> skills; 
=======
    @Column(name = "skills")
    private List<String> skills;

    @Column
    private String phone;

    @Column
    private Double rating;
>>>>>>> 1d8156104992512746a7fba2bfbc6a6f0d89bd3e

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Bid> bids;

<<<<<<< HEAD
    @ElementCollection
    private List<String> certifications; 
}
=======


}
>>>>>>> 1d8156104992512746a7fba2bfbc6a6f0d89bd3e
