package com.backend.reportservice.controller;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.entity.Report;
import com.backend.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports") // Tất cả API trong controller này sẽ có tiền tố /api/reports
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Cho phép frontend ở port 3000 gọi API
public class ReportController {

    private final ReportService reportService;

    @GetMapping("")
    public ResponseEntity<List<Report>> getAllReport() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ReportDto>> getReports(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String typeOfReport,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime reportAt
    ) {
        List<ReportDto> reports = reportService.getReports(status, typeOfReport, reportAt);
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getAllStatuses() {
        return ResponseEntity.ok(reportService.getAllStatuses());
    }

    @GetMapping("/severities")
    public ResponseEntity<List<String>> getAllSeverities() {
        return ResponseEntity.ok(reportService.getAllSeverities());
    }

    @GetMapping("/report-types")
    public ResponseEntity<List<String>> getAllCrimeTypes() {
        return ResponseEntity.ok(reportService.getAllCrimeTypes());
    }

    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportRequest reportRequest) {
        ReportDto savedReport = reportService.saveReport(reportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
    }
}