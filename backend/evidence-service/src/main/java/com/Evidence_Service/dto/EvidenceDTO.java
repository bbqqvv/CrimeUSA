package com.Evidence_Service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceDTO {
    private String evidenceId;
    private String description;
    private String detailedDescription;
    private String attachedFile;
    private String initialCondition;
    private String preservationMeasures;
    private String locationAtScene;
    private LocalDateTime createdAt;
    private String measureSurveyId;
    private String investigationPlanId;
    private String reportId;
    private String collectorUsername;
    private boolean isDeleted;
}
