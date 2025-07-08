package com.backend.reportservice.service;

import com.backend.reportservice.dto.ReportDto;
import java.util.List;

public interface ReportService {
    List<ReportDto> getReports(String status, String severity);
}