package com.Evidence_Service.repository;

import com.Evidence_Service.model.RecordInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordInfoRepository extends JpaRepository<RecordInfo, String> {
    List<RecordInfo> findByEvidenceId(String evidenceId);
}
