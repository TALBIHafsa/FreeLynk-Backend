package org.example.freelynk.controller;

import org.example.freelynk.model.Freelancer;
import org.example.freelynk.model.Project;
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
import java.util.*;

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


    @GetMapping("/{freelancerEmail}/saved-projects")
    public ResponseEntity<List<Project>> getSavedProjects(@PathVariable String freelancerEmail) {
        try {
            Freelancer freelancer = freelancerService.getFreelancerByEmail(freelancerEmail);
            List<Project> savedProjects = freelancerService.getSavedProject(freelancer);
            return ResponseEntity.ok(savedProjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Toggle saved project (add/remove)
    @PostMapping("/{freelancerEmail}/toggle-saved-project/{projectId}")
    public ResponseEntity<Map<String, Object>> toggleSavedProject(
            @PathVariable String freelancerEmail,
            @PathVariable UUID projectId) {
        try {
            boolean isSaved = freelancerService.toggleSavedProject(freelancerEmail, projectId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("isSaved", isSaved);
            response.put("message", isSaved ? "Project saved successfully" : "Project removed from saved");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Check if project is saved
    @GetMapping("/{freelancerEmail}/is-project-saved/{projectId}")
    public ResponseEntity<Map<String, Boolean>> isProjectSaved(
            @PathVariable String freelancerEmail,
            @PathVariable UUID projectId) {
        try {
            boolean isSaved = freelancerService.isProjectSaved(freelancerEmail, projectId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("isSaved", isSaved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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