package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    @Mapping(source = "report.id", target = "id")
    @Mapping(source = "report.typeReport", target = "typeReport")
    @Mapping(source = "report.severity", target = "severity")
    @Mapping(source = "report.description", target = "description")
    @Mapping(source = "report.caseLocation", target = "caseLocation")
    @Mapping(source = "report.reportedAt", target = "reportedAt")
    @Mapping(source = "report.reporterFullname", target = "reporterFullname")
    @Mapping(source = "report.reporterEmail", target = "reporterEmail")
    @Mapping(source = "report.reporterPhoneNumber", target = "reporterPhoneNumber")
    @Mapping(source = "report.status", target = "status")
    @Mapping(source = "report.officerApproveUsername", target = "officerApproveUsername")
    @Mapping(source = "report.caseId", target = "caseId")
    ReportDto toDto(Report report);
    Report toEntity(ReportDto reportDto);
    Report createReport(ReportRequest reportRequest);
}