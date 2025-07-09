package com.backend.reportservice.service;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.entity.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ReportDto> getReports(String status, String severity, String typeOfCrime, LocalDate date);
    List<Report> getAllReports();
    List<String> getAllStatuses();
    List<String> getAllSeverities();
    List<String> getAllCrimeTypes();
}