package com.Evidence_Service.repository;

import com.Evidence_Service.model.CaseEvidence;
import com.Evidence_Service.model.id.CaseEvidenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseEvidenceRepository extends JpaRepository<CaseEvidence, CaseEvidenceId> {
    List<CaseEvidence> findByCaseIdAndDeletedFalse(String caseId);
}
