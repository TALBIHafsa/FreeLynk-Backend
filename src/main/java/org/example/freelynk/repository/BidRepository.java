package org.example.freelynk.repository;

import org.example.freelynk.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    // Find all bids for a project (for client view)
    List<Bid> findByProjectId(Long projectId);

    // Find all bids by a freelancer (for freelancer dashboard)
    List<Bid> findByFreelancerId(Long freelancerId);
}