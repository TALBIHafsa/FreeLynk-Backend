package org.example.freelynk.controller;

import org.example.freelynk.dto.AddBidRequest;
import org.example.freelynk.dto.BidResponseDTO;
import org.example.freelynk.model.Bid;
import org.example.freelynk.model.BidStatus;
import org.example.freelynk.model.Freelancer;
import org.example.freelynk.security.SecurityUtil;
import org.example.freelynk.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBid(@RequestBody AddBidRequest request) {
        Freelancer freelancer = (Freelancer) SecurityUtil.getCurrentUser();
        Bid bid = bidService.addBid(request, freelancer);
        return ResponseEntity.ok(bid);
    }

 @GetMapping("/project/{projectId}/bids")
    public ResponseEntity<List<BidResponseDTO>> getBidsForProject(@PathVariable UUID projectId) {
        List<Bid> bids = bidService.getBidsForProject(projectId);
        
        List<BidResponseDTO> bidDTOs = bids.stream()
            .map(this::convertToBidResponseDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(bidDTOs);
    }

    private BidResponseDTO convertToBidResponseDTO(Bid bid) {
        BidResponseDTO dto = new BidResponseDTO();
        dto.setId(bid.getId());
        dto.setBidAmount(bid.getBidAmount());
        dto.setMotivation(bid.getMotivation());
        dto.setDeliveryDays(bid.getDeliveryDays());
        dto.setStatus(bid.getStatus());
        
        if (bid.getFreelancer() != null) {
            BidResponseDTO.FreelancerBasicDTO freelancerDTO = new BidResponseDTO.FreelancerBasicDTO();
            freelancerDTO.setId(bid.getFreelancer().getId());
            freelancerDTO.setFirstName(bid.getFreelancer().getFirstName());
            freelancerDTO.setLastName(bid.getFreelancer().getLastName());
            freelancerDTO.setEmail(bid.getFreelancer().getEmail());
            dto.setFreelancer(freelancerDTO);
        }
        
        return dto;
    }
    @GetMapping("/project/{projectId}/bidCount")
    public ResponseEntity<Integer> getBidCount(@PathVariable UUID projectId) {
    return ResponseEntity.ok(bidService.getBidsForProject(projectId).size());
}
@PutMapping("/{bidId}/accept")
public ResponseEntity<Void> acceptBid(@PathVariable UUID bidId) {
    bidService.updateBidStatus(bidId, BidStatus.ACCEPTED);
    return ResponseEntity.ok().build();
}


}
