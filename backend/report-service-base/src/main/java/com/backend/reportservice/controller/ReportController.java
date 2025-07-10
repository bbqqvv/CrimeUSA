package com.backend.reportservice.controller;

import com.backend.reportservice.dto.response.ApiResponse;
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
    public ApiResponse<List<Report>> getAllReport() {
        List<Report> reports = reportService.getAllReports();
        return ApiResponse.<List<Report>>builder()
                .code(200)
                .message("Get all reports")
                .data(reports)
                .build();
    }

    @GetMapping("/filter")
    public ApiResponse<List<ReportDto>> getReports(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String typeOfReport,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime reportAt
    ) {
        List<ReportDto> reports = reportService.getReports(status, typeOfReport, reportAt);
        return ApiResponse.<List<ReportDto>>builder()
                .code(200)
                .message("Get reports by filter")
                .data(reports)
                .build();
    }


    @GetMapping("/statuses")
    public ApiResponse<List<String>> getAllStatuses() {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Get statuses")
                .data(reportService.getAllStatuses())
                .build();
    }

    @GetMapping("/severities")
    public ApiResponse<List<String>> getAllSeverities() {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Get severities")
                .data(reportService.getAllSeverities())
                .build();
    }

    @GetMapping("/report-types")
    public ApiResponse<List<String>> getAllCrimeTypes() {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Get report types")
                .data(reportService.getAllCrimeTypes())
                .build();
    }

    @PostMapping
    public ApiResponse<ReportDto> createReport(@RequestBody ReportRequest reportRequest) {
        ReportDto savedReport = reportService.saveReport(reportRequest);
        return ApiResponse.<ReportDto>builder()
                .code(201)
                .message("Report created")
                .data(savedReport)
                .build();
    }
}