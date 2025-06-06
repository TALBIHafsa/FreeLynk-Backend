package org.example.freelynk.controller;

import org.example.freelynk.dto.AddBidRequest;
import org.example.freelynk.model.Bid;
import org.example.freelynk.model.Freelancer;
import org.example.freelynk.security.SecurityUtil;
import org.example.freelynk.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<Bid>> getBidsForProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(bidService.getBidsForProject(projectId));
    }
}
