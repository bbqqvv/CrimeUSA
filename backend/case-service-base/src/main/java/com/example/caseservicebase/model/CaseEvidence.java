/*
 * @ (#) CaseEvidenc.java 1.0 7/9/2025
 *
 * Copyright (c) 2025 IUH.All rights reserved
 */

package com.example.caseservicebase.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CaseEvidence {
    @Id
    @Column(name = "evidence_id")
    private Long evidenceId;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}