package org.example.freelynk.service;

import org.example.freelynk.model.Freelancer;
import org.example.freelynk.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Freelancer> getFreelancersBySkills(List<String> skills) {
        List<String> lowerCaseSkills = skills.stream().map(String::toLowerCase).collect(Collectors.toList());
        return freelancerRepository.findFreelancersBySkills(lowerCaseSkills);
    }


}