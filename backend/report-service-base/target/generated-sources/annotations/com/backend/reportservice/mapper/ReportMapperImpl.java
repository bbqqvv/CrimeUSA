package com.backend.reportservice.mapper;

import com.backend.reportservice.dto.request.ReportRequest;
import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.entity.Report;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-11T17:08:18+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportDto toDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDto.ReportDtoBuilder reportDto = ReportDto.builder();

        reportDto.id( report.getId() );
        reportDto.typeReport( report.getTypeReport() );
        reportDto.severity( report.getSeverity() );
        reportDto.description( report.getDescription() );
        reportDto.caseLocation( report.getCaseLocation() );
        reportDto.reportedAt( report.getReportedAt() );
        reportDto.reporterFullname( report.getReporterFullname() );
        reportDto.reporterEmail( report.getReporterEmail() );
        reportDto.reporterPhoneNumber( report.getReporterPhoneNumber() );
        reportDto.status( report.getStatus() );
        reportDto.officerApproveUsername( report.getOfficerApproveUsername() );
        reportDto.caseId( report.getCaseId() );
        reportDto.isDeleted( report.getIsDeleted() );

        return reportDto.build();
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
        report.setSeverity( reportDto.getSeverity() );
        report.setDescription( reportDto.getDescription() );
        report.setCaseLocation( reportDto.getCaseLocation() );
        report.setReportedAt( reportDto.getReportedAt() );
        report.setReporterFullname( reportDto.getReporterFullname() );
        report.setReporterEmail( reportDto.getReporterEmail() );
        report.setReporterPhoneNumber( reportDto.getReporterPhoneNumber() );
        report.setStatus( reportDto.getStatus() );
        report.setOfficerApproveUsername( reportDto.getOfficerApproveUsername() );
        report.setIsDeleted( reportDto.getIsDeleted() );

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
