package com.Evidence_Service.repository;

import com.Evidence_Service.model.PhysicalInvestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalInvestResultRepository extends JpaRepository<PhysicalInvestResult, String> {
    List<PhysicalInvestResult> findByEvidenceId(String evidenceId);
}
