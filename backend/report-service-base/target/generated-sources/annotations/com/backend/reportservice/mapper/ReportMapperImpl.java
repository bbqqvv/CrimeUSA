package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.entity.Report;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T14:10:10+0700",
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

        reportDto.setId( report.getId() );
        reportDto.setCaseId( report.getCaseId() );
        reportDto.setTypeReport( report.getTypeReport() );
        reportDto.setDescription( report.getDescription() );
        reportDto.setCaseLocation( report.getCaseLocation() );
        reportDto.setReportedAt( report.getReportedAt() );
        reportDto.setReporterFullname( report.getReporterFullname() );
        reportDto.setReporterEmail( report.getReporterEmail() );
        reportDto.setReporterPhoneNumber( report.getReporterPhoneNumber() );
        reportDto.setStatus( report.getStatus() );
        reportDto.setOfficerApproveUsername( report.getOfficerApproveUsername() );

        return reportDto;
    }

    @Override
    public Report toEntity(ReportDto reportDto) {
        if ( reportDto == null ) {
            return null;
        }

        Report report = new Report();

        report.setId( reportDto.getId() );
        report.setCaseId( reportDto.getCaseId() );
        report.setTypeReport( reportDto.getTypeReport() );
        report.setDescription( reportDto.getDescription() );
        report.setCaseLocation( reportDto.getCaseLocation() );
        report.setReportedAt( reportDto.getReportedAt() );
        report.setReporterFullname( reportDto.getReporterFullname() );
        report.setReporterEmail( reportDto.getReporterEmail() );
        report.setReporterPhoneNumber( reportDto.getReporterPhoneNumber() );
        report.setStatus( reportDto.getStatus() );
        report.setOfficerApproveUsername( reportDto.getOfficerApproveUsername() );

        return report;
    }

    @Override
    public Report createReport(ReportRequest reportRequest) {
        if ( reportRequest == null ) {
            return null;
        }

        Report report = new Report();

        report.setTypeReport( reportRequest.getTypeReport() );
        report.setDescription( reportRequest.getDescription() );
        report.setCaseLocation( reportRequest.getCaseLocation() );
        report.setReporterFullname( reportRequest.getReporterFullname() );
        report.setReporterEmail( reportRequest.getReporterEmail() );
        report.setReporterPhoneNumber( reportRequest.getReporterPhoneNumber() );

        return report;
    }
}
