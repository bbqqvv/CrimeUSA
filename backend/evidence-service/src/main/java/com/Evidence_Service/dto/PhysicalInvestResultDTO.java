package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalInvestResultDTO {
    @NotBlank
    @NotNull
    private String resultId;

    @NotBlank
    @NotNull
    private String investigationPlanId;

    @NotBlank
    @NotNull
    private String evidenceId;

    @NotBlank
    private String status;

    @NotBlank
    private String notes;

    @NotBlank
    private String imageUrl;

    @NotNull
    private LocalDateTime createdAt;

    private boolean isDeleted;
}
