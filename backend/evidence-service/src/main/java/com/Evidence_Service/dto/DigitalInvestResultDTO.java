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
public class DigitalInvestResultDTO {
    @NotBlank
    @NotNull
    private String resultId;

    @NotBlank
    private String investigationPlanId;

    @NotBlank
    private String evidenceId;

    @NotBlank
    private String deviceType;

    @NotBlank
    private String analystTool;

    @NotBlank
    private String result;

    @NotNull
    private LocalDateTime createdAt;

    private boolean isDeleted;
}
