package com.Evidence_Service.repository;

import com.Evidence_Service.model.ForensicInvestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForensicInvestResultRepository extends JpaRepository<ForensicInvestResult, String> {
    Optional<ForensicInvestResult> findByResultIdAndIsDeletedFalse(String resultId);
    Page<ForensicInvestResult> findByEvidenceIdAndIsDeletedFalse(String evidenceId, Pageable pageable);
    Page<ForensicInvestResult> findByInvestigationPlanIdAndIsDeletedFalse(String investigationId, Pageable pageable);
    boolean existsByEvidenceIdAndIsDeletedFalse(String evidenceId);
}
