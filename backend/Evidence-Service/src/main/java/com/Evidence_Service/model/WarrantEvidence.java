package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import com.Evidence_Service.model.id.WarrantEvidenceId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "warrant_evidence")
@IdClass(WarrantEvidenceId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WarrantEvidence extends BaseClass {

    @Id
    @Column(name = "warrant_id")
    private String warrantId;

    @Id
    @Column(name = "evidence_id")
    private String evidenceId;

    private LocalDateTime assignedAt;

}
