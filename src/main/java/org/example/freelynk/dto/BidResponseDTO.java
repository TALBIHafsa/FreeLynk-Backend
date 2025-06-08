package org.example.freelynk.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.freelynk.model.BidStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidResponseDTO {
    private UUID id;
    private Double bidAmount;
    private String motivation;
    private Integer deliveryDays;
    private BidStatus status;
    private LocalDateTime submittedAt;
    private FreelancerBasicDTO freelancer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FreelancerBasicDTO {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
    }
}