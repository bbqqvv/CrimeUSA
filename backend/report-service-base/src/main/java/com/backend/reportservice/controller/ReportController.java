package com.backend.reportservice.controller;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports") // Tất cả API trong controller này sẽ có tiền tố /api/reports
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Cho phép frontend ở port 3000 gọi API
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDto>> getReports(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String severity
    ) {
        List<ReportDto> reports = reportService.getReports(status, severity);
        return ResponseEntity.ok(reports);
    }
}