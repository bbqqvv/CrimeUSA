package com.Evidence_Service.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalInvestResultDTO {
    private String resultId;
    private String investigationPlanId;
    private String evidenceId;
    private String deviceType;
    private String analystTool;
    private String result;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
