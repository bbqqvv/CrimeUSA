package com.backend.reportservice.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportRequest {
  private String typeReport;
  private String description;
  private String caseLocation;

  private String reporterFullname;
  private String reporterEmail;
  private String reporterPhoneNumber;
}