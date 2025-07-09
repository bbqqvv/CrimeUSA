package com.backend.reportservice.service;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.entity.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<ReportDto> getReports(String status, String typeOfReport, LocalDateTime reportAt);
    ReportDto saveReport(ReportRequest reportRequest);
    List<Report> getAllReports();
    List<String> getAllStatuses();
    List<String> getAllSeverities();
    List<String> getAllCrimeTypes();
}