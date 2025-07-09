package com.backend.reportservice.controller;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.entity.Report;
import com.backend.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports") // Tất cả API trong controller này sẽ có tiền tố /api/reports
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Cho phép frontend ở port 3000 gọi API
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/filter")
    public ResponseEntity<List<ReportDto>> getReports(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String typeOfCrime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<ReportDto> reports = reportService.getReports(status, severity, typeOfCrime, date);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("")
    public ResponseEntity<List<Report>> getAllReport() {
        List<Report> reports = reportService.getAllReports();
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

    @GetMapping("/crime-types")
    public ResponseEntity<List<String>> getAllCrimeTypes() {
        return ResponseEntity.ok(reportService.getAllCrimeTypes());
    }
}