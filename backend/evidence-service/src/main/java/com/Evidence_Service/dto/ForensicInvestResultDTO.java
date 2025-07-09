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
public class ForensicInvestResultDTO {
    @NotNull
    @NotBlank
    private String resultId;

    @NotNull
    @NotBlank
    private String investigationPlanId;

    @NotNull
    @NotBlank
    private String evidenceId;

    @NotBlank
    private String labName;

    @NotBlank
    private String report;

    @NotBlank
    private String resultSummary;

    @NotBlank
    private String notes;

    @NotBlank
    private String imageUrl;



    @NotNull
    private LocalDateTime createdAt;

    private boolean isDeleted;
}
