package org.example.freelynk.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddBidRequest {
    private UUID projectId;
    private Double bidAmount;
    private Integer deliveryDays;
    private String motivation;

}
