package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import com.Evidence_Service.model.id.ReportEvidenceId;
import com.Evidence_Service.model.id.SuspectEvidenceId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "report_evidence")
@IdClass(ReportEvidenceId.class)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReportEvidence extends BaseClass {
    @Id
    @Column(name = "report_id")
    private String reportId;

    @Id
    @Column(name = "evidence_id")
    private String evidenceId;

    private String attachedBy;
}
