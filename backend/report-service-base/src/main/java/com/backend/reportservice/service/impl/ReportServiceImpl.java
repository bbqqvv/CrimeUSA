package com.backend.reportservice.service;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.entity.Report;
import com.backend.reportservice.mapper.ReportMapper;
import com.backend.reportservice.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: Tự tạo constructor cho các trường final
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportDto> getReports(String status, String severity, String typeOfCrime, LocalDate date) {
        return reportRepository.findAll()
                .stream()
                .filter(report -> status == null || report.getStatus().equalsIgnoreCase(status))
                .filter(report -> severity == null || report.getSeverity().equalsIgnoreCase(severity))
                .filter(report -> typeOfCrime == null || report.getTypeOfCrime().equalsIgnoreCase(typeOfCrime))
                .filter(report -> date == null || report.getDate().isEqual(date))
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
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
        return reportRepository.findAll()
                .stream()
                .map(Report::getSeverity)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCrimeTypes() {
        return reportRepository.findAll()
                .stream()
                .map(Report::getTypeOfCrime)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

}