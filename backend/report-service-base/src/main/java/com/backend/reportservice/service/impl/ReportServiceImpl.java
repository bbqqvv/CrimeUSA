package com.backend.reportservice.service.impl;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.entity.Report;
import com.backend.reportservice.mapper.ReportMapper;
import com.backend.reportservice.repository.ReportRepository;
import com.backend.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: Tự tạo constructor cho các trường final
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportDto> getReports(String status, String typeOfReport, LocalDateTime reportAt) {
        return reportRepository.findAll()
                .stream()
                .filter(report -> status == null || report.getStatus().equalsIgnoreCase(status))
                .filter(report -> typeOfReport == null || report.getTypeReport().equalsIgnoreCase(typeOfReport))
                .filter(report -> reportAt == null || report.getReportedAt().isEqual(reportAt))
                .map(report -> {
                    System.out.println("Before mapping: " + report);
                    ReportDto dto = reportMapper.toDto(report);
                    System.out.println("After mapping: " + dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ReportDto saveReport(ReportRequest reportRequest) {
        Report report = reportMapper.createReport(reportRequest);
        report.setReportedAt(LocalDateTime.now());
        report.setStatus("Pending");
        reportRepository.save(report);
        return reportMapper.toDto(report);
    }


    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<String> getAllStatuses() {
        return reportRepository.findAll()
                .stream()
                .map(Report::getStatus)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllSeverities() {
//        return reportRepository.findAll()
//                .stream()
//                .map(Report::getSeverity)
//                .filter(Objects::nonNull)
//                .distinct()
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<String> getAllCrimeTypes() {
        return reportRepository.findAll()
                .stream()
                .map(Report::getTypeReport)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}