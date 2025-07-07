package com.backend.investigationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvestigationPlanCreationRequest {
    @JsonProperty("summary")
    @NotBlank
    String summary;

    @JsonProperty("create_at")
    @NotNull
    LocalDateTime createAt;

    @JsonProperty("deadline_date")
    @NotNull
    LocalDateTime deadlineDate;

    @JsonProperty("status")
    @NotBlank
    String status;

    @JsonProperty("plan_content")
    String planContent;

    @JsonProperty("type")
    String type;

    @JsonProperty("holiday_conflict")
    String holidayConflict;

    @JsonProperty("created_officer_name")
    String createdOfficerName;

    @JsonProperty("accepted_officer_name")
    String acceptedOfficerName;

    @JsonProperty("case_id")
    String  caseId;
}
