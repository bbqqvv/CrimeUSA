package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.entity.Report;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T14:39:05+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportDto toDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDto reportDto = new ReportDto();

        return reportDto;
    }

    @Override
    public Report toEntity(ReportDto reportDto) {
        if ( reportDto == null ) {
            return null;
        }

        Report report = new Report();

        return report;
    }

    @Override
    public Report createReport(ReportRequest reportRequest) {
        if ( reportRequest == null ) {
            return null;
        }

        Report report = new Report();

        return report;
    }
}
