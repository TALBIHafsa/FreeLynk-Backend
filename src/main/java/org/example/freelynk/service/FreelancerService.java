package org.example.freelynk.service;

import org.example.freelynk.model.Freelancer;
import org.example.freelynk.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;

    @Autowired
    public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }



    public List<Freelancer> getFreelancers() {
        return freelancerRepository.findAll();
    }

    public Freelancer getFreelancerById(UUID freelancerId) {
        return freelancerRepository.findById(freelancerId).orElse(null);
    }

    public Freelancer getFreelancerByEmail(String email) {
        return freelancerRepository.findByEmail(email).orElse(null);
    }

public List<Freelancer> getFreelancersBySkills(List<String> skills) {
    List<Freelancer> result = new ArrayList<>();
    for (String skill : skills) {
        // Try exact match first
        List<String> lowerCaseSkills = Collections.singletonList(skill.toLowerCase());
        List<Freelancer> exactMatch = freelancerRepository.findFreelancersBySkills(lowerCaseSkills);
        
        if (exactMatch.isEmpty()) {
            // Try partial match
            List<Freelancer> partialMatch = freelancerRepository.findFreelancersBySkillContaining(skill);
            result.addAll(partialMatch);
        } else {
            result.addAll(exactMatch);
        }
    }
    return result.stream().distinct().collect(Collectors.toList());
}


}