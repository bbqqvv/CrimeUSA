package com.backend.reportservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "type_report")
    private String typeReport;

    private String description;

    @Column(name = "case_location")
    private String caseLocation;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    @Column(name = "reporter_fullname")
    private String reporterFullname;

    @Column(name = "reporter_email")
    private String reporterEmail;

    @Column(name = "reporter_phonenumber")
    private String reporterPhoneNumber;

    private String status;

    @Column(name = "officer_approve_username")
    private String officerApproveUsername;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}