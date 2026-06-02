package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.entity.Report;
import com.backend.reportservice.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "statusToString")
    ReportDto toDto(Report report);

    Report toEntity(ReportDto reportDto);

    Report createReport(ReportRequest reportRequest);

    @Named("statusToString")
    default String statusToString(Status status) {
        return status == null ? null : status.name();
    }
}