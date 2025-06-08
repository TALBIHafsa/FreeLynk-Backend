package org.example.freelynk.controller;

import org.example.freelynk.model.Freelancer;
import org.example.freelynk.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // Map URL slugs to actual skill names
    String skillName = mapCategoryToSkill(category);
    List<Freelancer> freelancers = freelancerService.getFreelancersBySkills(Collections.singletonList(skillName));
    return new ResponseEntity<>(freelancers, HttpStatus.OK);
}

private String mapCategoryToSkill(String category) {
    Map<String, String> categoryMap = new HashMap<>();
    categoryMap.put("web-development", "Web Development");
    categoryMap.put("graphic-design", "Graphic Design");
    categoryMap.put("writing-translation", "Writing");
    categoryMap.put("digital-marketing", "Digital Marketing");
    categoryMap.put("video-animation", "Video Editing");
    categoryMap.put("business-assistance", "Business");
    
    return categoryMap.getOrDefault(category, category);
}





}