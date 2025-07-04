package com.Evidence_Service.repository;

import com.Evidence_Service.model.ForensicInvestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForensicInvestResultRepository extends JpaRepository<ForensicInvestResult, String> {
    List<ForensicInvestResult> findByEvidenceId(String evidenceId);
}
