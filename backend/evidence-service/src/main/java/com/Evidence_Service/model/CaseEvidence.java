package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import com.Evidence_Service.model.id.CaseEvidenceId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CaseEvidenceId.class)
public class CaseEvidence extends BaseClass {
    @Id
    @Column(name = "case_id")
    private String caseId;

    @Id
    @Column(name = "evidence_id")
    private String evidenceId;

}
