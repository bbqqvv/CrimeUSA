package com.backend.investigationservice.repository;

import com.backend.investigationservice.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID>, JpaSpecificationExecutor<Interview> {
    List<Interview> findByInvestigationPlan_CaseId(UUID caseId);
    List<Interview> findByIsDeletedFalse();
    List<Interview> findByInvestigationPlan_InvestigationPlanIdAndIsDeletedFalse(UUID investigationPlanId);
}
