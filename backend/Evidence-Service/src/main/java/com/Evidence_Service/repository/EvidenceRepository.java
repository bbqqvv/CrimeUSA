package com.Evidence_Service.repository;

import com.Evidence_Service.model.Evidence;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, String> {
    List<Evidence> findByInvestigationPlanId(String investigationPlanId);

    List<Evidence> findByReportId(String reportId);

    List<Evidence> findByCollectorUsername(String collectorUsername);

    List<Evidence> findByMeasureSurveyId(String measureSurveyId);

    List<Evidence> findByIsDeletedFalse();

    boolean existedByEvidenceId(String evidenceId);

    List<Evidence> findByDescriptionContainingIgnoreCase(String keyword);

    List<Evidence> findByInvestigationPlanIdAndIsDeletedFalse(String investigationPlanId);

    List<Evidence> findByDateCollectedBetween(LocalDateTime start, LocalDateTime end);
}
