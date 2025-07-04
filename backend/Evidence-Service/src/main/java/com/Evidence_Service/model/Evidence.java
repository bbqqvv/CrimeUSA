package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evidence")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Evidence extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "evidence_id")
    private String evidenceId;

    @Column(name = "case_id")
    private String caseId;

    @Column(name = "report_id")
    private String reportId;

    @Column(name = "warrantResult_id")
    private String warrantResultId;

    @Column(name = "measure_survey_id")
    private String measureSurveyId;

    @Column(name = "investigation_plan_id")
    private String investigationPlanId;

    private String description;
    private String collectorUsername;
    private String detailedDescription;
    private String initialCondition;
    private String preservationMeasures;
    private String locationAtScene;
    private String currentLocation;
    private String attachedFile;
    private EvidenceStatus status;
}
