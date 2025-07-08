package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasureSurveyDTO {
    @NotNull
    @NotBlank
    private String measureSurveyId;

    @NotBlank
    private String source;

    @NotBlank
    private String typeName;

    @NotBlank
    @NotNull
    private String resultId;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
