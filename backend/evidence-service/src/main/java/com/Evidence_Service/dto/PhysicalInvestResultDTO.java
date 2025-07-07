package com.Evidence_Service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalInvestResultDTO {
    private String resultId;
    private String investigationPlanId;
    private String evidenceId;
    private String status;
    private String notes;
    private String imageUrl;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
