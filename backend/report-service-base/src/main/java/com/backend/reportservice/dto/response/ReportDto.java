package com.backend.reportservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {
    private Long id;
    private Long caseId;
    private String typeReport;
    private String severity;

    private String description;
    private String caseLocation;
    private LocalDateTime reportedAt;
    private String reporterFullname;
    private String reporterEmail;
    private String reporterPhoneNumber;

    private String status;
    private String officerApproveUsername;
    private Boolean isDeleted = false;
}