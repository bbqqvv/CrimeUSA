/*
 * @ (#) CaseArrest.java 1.0 7/9/2025
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
public class CaseArrest {
    @Id
    @Column(name = "arrest_id")
    private Long arrestId;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseId;

    @Column(name = "suspect_id")
    private Long suspectId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}