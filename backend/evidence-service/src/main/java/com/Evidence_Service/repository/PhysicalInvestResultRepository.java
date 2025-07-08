package com.Evidence_Service.repository;

import com.Evidence_Service.entity.PhysicalInvestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalInvestResultRepository extends JpaRepository<PhysicalInvestResult, String> {
    Page<PhysicalInvestResult> findByEvidenceId(String evidenceId, Pageable pageable);
    boolean existsByResultId(String resultId);
    PhysicalInvestResult findByResultId(String resultId);


}
