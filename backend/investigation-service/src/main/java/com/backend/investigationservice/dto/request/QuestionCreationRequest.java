package com.backend.investigationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCreationRequest {
    @JsonProperty("created_by")
    @NotBlank
    private String createdBy;

    @JsonProperty("content")
    @NotBlank
    private String content;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("reliability")
    private String reliability;

    @JsonProperty("interview_id")
    @NotNull
    private UUID interviewId;
}
