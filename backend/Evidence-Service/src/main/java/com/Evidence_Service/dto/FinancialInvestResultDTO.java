package com.Evidence_Service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInvestResultDTO {
    private String resultId;
    private String investigationPlanId;
    private String evidenceId;
    private String summary;
    private String attachedFile;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
