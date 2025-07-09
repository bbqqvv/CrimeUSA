/*
 * @ (#) Case.java 1.0 7/9/2025
 *
 * Copyright (c) 2025 IUH.All rights reserved
 */

package com.example.caseservicebase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cases")
@NoArgsConstructor
public class Case {
    @Id
    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "case_number")
    private String caseNumber;

    @Column(name = "type_case")
    private String typeCase;

    @Column(name = "severity")
    private String severity;

    @Column(name = "status")
    private String status;

    @Column(name = "summary")
    private String summary;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "case_target")
    private String caseTarget;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}