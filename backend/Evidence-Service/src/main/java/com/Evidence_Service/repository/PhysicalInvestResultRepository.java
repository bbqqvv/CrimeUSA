package com.Evidence_Service.repository;

import com.Evidence_Service.model.PhysicalInvestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalInvestResultRepository extends JpaRepository<PhysicalInvestResult, String> {
    Page<PhysicalInvestResult> findByEvidenceId(String evidenceId, Pageable pageable);
    boolean existsByResultId(String resultId);
    PhysicalInvestResult findByResultId(String resultId);


}
