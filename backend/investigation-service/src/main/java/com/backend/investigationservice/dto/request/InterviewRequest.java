package com.backend.investigationservice.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterviewRequest {
    @JsonProperty("investigation_plan_id")
    UUID investigationPlanId;

    @JsonProperty("location")
    String location;

    @JsonProperty("attached_file")
    String attachedFile;

    @JsonProperty("start_time")
    LocalDateTime startTime;

    @JsonProperty("end_time")
    LocalDateTime endTime;

    @JsonProperty("holiday_conflict")
    String holidayConflict;

    @JsonProperty("holiday_id")
    UUID holidayId;
}