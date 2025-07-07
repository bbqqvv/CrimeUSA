package com.backend.investigationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.investigationservice.model.InvestigationPlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestigationPlanRepository extends JpaRepository<InvestigationPlan, UUID>, JpaSpecificationExecutor<InvestigationPlan> {
    List<InvestigationPlan> findByCaseId(UUID caseId);
    List<InvestigationPlan> findByIsDeletedFalse();

}
