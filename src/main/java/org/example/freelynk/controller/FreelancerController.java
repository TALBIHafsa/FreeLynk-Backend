package org.example.freelynk.controller;

import org.example.freelynk.model.Freelancer;
import org.example.freelynk.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/freelancers")
public class FreelancerController {

    private final FreelancerService freelancerService;

    @Autowired
    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }



    @GetMapping
    public ResponseEntity<List<Freelancer>> getFreelancers() {
        List<Freelancer> freelancers = freelancerService.getFreelancers();
        return new ResponseEntity<>(freelancers, HttpStatus.OK);
    }

    @GetMapping("/{freelancerId}")
    public ResponseEntity<Freelancer> getFreelancerById(@PathVariable UUID freelancerId) {
        Freelancer freelancer = freelancerService.getFreelancerById(freelancerId);
        if (freelancer != null) {
            return new ResponseEntity<>(freelancer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{freelancerEmail}")
    public ResponseEntity<Freelancer> getFreelancerByEmail(@PathVariable String freelancerEmail) {
        Freelancer freelancer = freelancerService.getFreelancerByEmail(freelancerEmail);
        if (freelancer != null) {
            return new ResponseEntity<>(freelancer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Freelancer>> getFreelancersByCategory(@PathVariable String category) {
        List<Freelancer> freelancers = freelancerService.getFreelancersBySkills(Collections.singletonList(category));
        return new ResponseEntity<>(freelancers, HttpStatus.OK);
    }

    public static class FreelancerPublicDTO {
        public String id;
        public String firstName;
        public String lastName;
        public String email;
        public Double rating;
        public String occupation;

        public FreelancerPublicDTO(Freelancer freelancer) {
            this.id = freelancer.getId().toString();
            this.firstName = freelancer.getFirstName();
            this.lastName = freelancer.getLastName();
            this.email = freelancer.getEmail();
            this.rating = freelancer.getRating();
            this.occupation = freelancer.getOccupation();
        }
    }



}