package com.Evidence_Service.repository;

import com.Evidence_Service.model.WarrantEvidence;
import com.Evidence_Service.model.id.WarrantEvidenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrantEvidenceRepository extends JpaRepository<WarrantEvidence, WarrantEvidenceId> {
    List<WarrantEvidence> findByEvidenceIdAndDeletedFalse(String evidenceId);
}
