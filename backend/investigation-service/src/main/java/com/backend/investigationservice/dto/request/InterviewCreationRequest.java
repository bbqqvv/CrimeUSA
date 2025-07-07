package com.backend.investigationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewCreationRequest {
    @JsonProperty("investigation_plan_id")
    private UUID investigationPlanId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("attached_file")
    private String attachedFile;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("holiday_conflict")
    private String holidayConflict;

    @JsonProperty("holiday_id")
    private String holidayId;
} 