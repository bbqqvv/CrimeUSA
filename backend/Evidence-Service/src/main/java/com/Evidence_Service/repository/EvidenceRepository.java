package com.Evidence_Service.repository;

import com.Evidence_Service.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, String> {
    List<Evidence> findByInvestigationPlanId(String investigationPlanId);

    List<Evidence> findByReportId(String reportId);

    List<Evidence> findByCollectorUsername(String collectorUsername);

    List<Evidence> findByMeasureSurveyId(String measureSurveyId);

    List<Evidence> findByIsDeletedFalse();

    boolean existsByEvidenceId(String evidenceId);

    List<Evidence> findByDescriptionContainingIgnoreCase(String keyword);

    List<Evidence> findByInvestigationPlanIdAndIsDeletedFalse(String investigationPlanId);

}
