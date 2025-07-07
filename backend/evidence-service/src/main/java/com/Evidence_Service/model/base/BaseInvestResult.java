package com.Evidence_Service.model.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseInvestResult extends BaseClass{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "result_id")
    protected String resultId;

    @Column(name = "investigation_plan_id")
    protected String investigationPlanId;

    @Column(name = "evidence_id")
    protected String evidenceId;
}