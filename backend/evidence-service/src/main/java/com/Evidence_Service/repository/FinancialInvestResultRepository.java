package com.Evidence_Service.repository;

import com.Evidence_Service.entity.FinancialInvestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialInvestResultRepository extends JpaRepository<FinancialInvestResult, String> {
    Page<FinancialInvestResult> findByEvidenceId(String evidenceId, Pageable pageable);
    FinancialInvestResult findByResultId(String resultId);
}
