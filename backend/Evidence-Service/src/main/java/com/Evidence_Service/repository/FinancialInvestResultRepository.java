package com.Evidence_Service.repository;

import com.Evidence_Service.model.FinancialInvestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInvestResultRepository extends JpaRepository<FinancialInvestResult, String> {
    List<FinancialInvestResult> findByEvidenceId(String evidenceId);
}
