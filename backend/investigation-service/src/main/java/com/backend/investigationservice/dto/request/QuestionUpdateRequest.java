package com.backend.investigationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionUpdateRequest {
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("content")
    private String content;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("reliability")
    private String reliability;

    @JsonProperty("interview_id")
    private UUID interviewId;
} 