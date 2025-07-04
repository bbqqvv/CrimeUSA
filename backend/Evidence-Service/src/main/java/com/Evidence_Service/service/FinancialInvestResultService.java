package com.Evidence_Service.service;

import com.Evidence_Service.dto.FinancialInvestResultDTO;

import java.util.List;

public interface FinancialInvestResultService {
    FinancialInvestResultDTO addFinancialInvestResult(String evidenceId, FinancialInvestResultDTO dto);

    FinancialInvestResultDTO getFinancialInvestById(String id);

    List<FinancialInvestResultDTO> getAllFinancialInvestByEvidenceId(String evidenceId);

    FinancialInvestResultDTO updateFinancialInvest(String evidenceId, String resultId, FinancialInvestResultDTO dto);

    void deleteFinancialInvest(String id);
}
