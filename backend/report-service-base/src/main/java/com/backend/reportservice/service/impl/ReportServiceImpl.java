package com.backend.reportservice.service;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.mapper.ReportMapper;
import com.backend.reportservice.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: Tự tạo constructor cho các trường final
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportDto> getReports(String status, String severity) {
        // Lấy tất cả report từ DB
        return reportRepository.findAll()
                .stream()
                // Lọc theo status nếu có
                .filter(report -> status == null || report.getStatus().equalsIgnoreCase(status))
                // Lọc theo severity nếu có
                .filter(report -> severity == null || report.getSeverity().equalsIgnoreCase(severity))
                // Chuyển đổi từ Entity sang DTO
                .map(reportMapper::toDto)
                // Gom thành một List
                .collect(Collectors.toList());
    }
}