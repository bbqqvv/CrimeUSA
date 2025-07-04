package com.Evidence_Service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForensicInvestResultDTO {
    private String resultId;
    private String investigationPlanId;
    private String evidenceId;
    private String labName;
    private String report;
    private String resultSummary;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
