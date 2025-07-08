package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceDTO {
    @NotNull
    @NotBlank
    private String evidenceId;

    @NotBlank
    private String description;

    @NotBlank
    private String detailedDescription;

    @NotBlank
    private String attachedFile;

    @NotBlank
    private String initialCondition;

    @NotBlank
    private String preservationMeasures;

    @NotBlank
    private String locationAtScene;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    private String measureSurveyId;

    @NotBlank
    private String investigationPlanId;

    @NotBlank
    private String reportId;

    @NotBlank
    private String collectorUsername;

    private boolean isDeleted;
}
