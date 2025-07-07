package com.backend.investigationservice.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvestigationPlanResponse {
    UUID investigationPlanId;
    String summary;
    LocalDateTime createAt;
    LocalDateTime deadlineDate;
    String status;
    String planContent;
    String type;
    String holidayConflict;
    String createdOfficerName;
    String acceptedOfficerName;
    UUID caseId;
}