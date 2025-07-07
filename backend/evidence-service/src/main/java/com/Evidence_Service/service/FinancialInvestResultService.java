package com.Evidence_Service.service;

import com.Evidence_Service.dto.FinancialInvestResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialInvestResultService {
    FinancialInvestResultDTO addFinancialInvestResult(String evidenceId, FinancialInvestResultDTO dto);

    FinancialInvestResultDTO getFinancialInvestById(String resultId);

    Page<FinancialInvestResultDTO> getAllFinancialInvestByEvidenceId(String evidenceId, Pageable pageable);

    FinancialInvestResultDTO updateFinancialInvest(String evidenceId, String resultId, FinancialInvestResultDTO dto);

    void deleteFinancialInvest(String resultId);
}
