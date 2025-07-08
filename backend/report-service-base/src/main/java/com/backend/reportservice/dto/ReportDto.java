package com.backend.reportservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReportDto {
    private Long id;
    private String typeOfCrime;
    private String severity;
    private LocalDate date;
    private String reporter;
    private String status;
}