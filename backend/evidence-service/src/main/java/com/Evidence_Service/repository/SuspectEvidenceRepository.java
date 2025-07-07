package com.Evidence_Service.repository;

import com.Evidence_Service.model.SuspectEvidence;
import com.Evidence_Service.model.id.SuspectEvidenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuspectEvidenceRepository extends JpaRepository<SuspectEvidence, SuspectEvidenceId> {

    List<SuspectEvidence> findByEvidenceId(String evidenceId);

    boolean existsBySuspectIdAndEvidenceId(String suspectId, String evidenceId);
    boolean existsBySuspectIdAndIsDeletedFalse(String suspectId);

    List<SuspectEvidence> findBySuspectIdAndIsDeletedFalse(String suspectId);
    List<SuspectEvidence> findByEvidenceIdAndIsDeletedFalse(String evidenceId);
    void deleteBySuspectIdAndEvidenceId(String suspectId, String evidenceId);
}
