package com.backend.investigationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    @JsonProperty("question_id")
    private UUID questionId;

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

    @JsonProperty("deleted")
    private boolean deleted;
}