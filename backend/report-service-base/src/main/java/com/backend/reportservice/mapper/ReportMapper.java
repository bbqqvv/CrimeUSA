package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.ReportDto;
import com.backend.reportservice.entity.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDto toDto(Report report);
    Report toEntity(ReportDto reportDto);
}