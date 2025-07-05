package com.Evidence_Service.dto;

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
    private String measureSurveyId;
    private String source;
    private String typeName;
    private String resultId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime isDeleted;
}
