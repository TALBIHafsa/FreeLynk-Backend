package org.example.freelynk.service;

import org.example.freelynk.dto.AddBidRequest;
import org.example.freelynk.model.*;
import org.example.freelynk.repository.BidRepository;
import org.example.freelynk.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BidService {

    private final BidRepository bidRepository;
    private final ProjectRepository projectRepository;

    public BidService(BidRepository bidRepository, ProjectRepository projectRepository) {
        this.bidRepository = bidRepository;
        this.projectRepository = projectRepository;
    }

    public Bid addBid(AddBidRequest request, Freelancer freelancer) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Bid bid = new Bid();
        bid.setFreelancer(freelancer);
        bid.setProject(project);
        bid.setBidAmount(request.getBidAmount());
        bid.setDeliveryDays(request.getDeliveryDays());
        bid.setStatus(BidStatus.PENDING);
        bid.setMotivation(request.getMotivation());

        return bidRepository.save(bid);
    }

    public List<Bid> getBidsForProject(UUID projectId) {
        return bidRepository.findByProjectId(projectId);
    }

    @Transactional
    public void updateBidStatus(UUID bidId, BidStatus newStatus) {
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));

        if (newStatus == BidStatus.ACCEPTED && bid.getStatus() != BidStatus.ACCEPTED) {
            bid.setStatus(BidStatus.ACCEPTED);
            bidRepository.save(bid);

            Project project = bid.getProject();
            project.setBidNumber(project.getBidNumber() + 1);
            projectRepository.save(project);
        } else {
            bid.setStatus(newStatus);
            bidRepository.save(bid);
        }
    }

}
