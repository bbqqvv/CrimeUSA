package com.Evidence_Service.repository;

import com.Evidence_Service.entity.RecordInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordInfoRepository extends JpaRepository<RecordInfo, String> {
    Page<RecordInfo> findByEvidenceIdAndIsDeletedFalse(String evidenceId, Pageable pageable);
    Page<RecordInfo> findByIsDeletedFalse(Pageable pageable);
    Optional<RecordInfo> findByRecordInfoIdAndIsDeletedFalse(String recordInfoId);
    boolean existsByRecordInfoIdAndIsDeletedFalse(String recordInfoId);
}
