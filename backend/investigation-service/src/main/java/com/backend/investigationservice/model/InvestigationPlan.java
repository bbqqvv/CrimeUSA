package com.backend.investigationservice.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "investigation_plans")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InvestigationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "investigation_plan_id", nullable = false)
    private UUID investigationPlanId;

    @Column(name = "summary")
    private String summary;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "deadline_date")
    private LocalDateTime deadlineDate;

    @Column(name = "status")
    private String status;

    @Column(name = "plan_content")
    private String planContent;

    @Column(name = "type")
    private String type;

    @Column(name = "holiday_conflict")
    private String holidayConflict;

    @Column(name = "created_officer_name")
    private String createdOfficerName;

    @Column(name = "accepted_officer_name")
    private String acceptedOfficerName;

    @Column(name = "case_id")
    private UUID caseId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "investigationPlan", cascade = CascadeType.ALL)
    private List<Interview> interviews;

}
