package com.Evidence_Service.repository;

import com.Evidence_Service.model.FinancialInvestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInvestResultRepository extends JpaRepository<FinancialInvestResult, String> {
    Page<FinancialInvestResult> findByEvidenceId(String evidenceId, Pageable pageable);
    FinancialInvestResult findByResultId(String resultId);
}
