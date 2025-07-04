package com.Evidence_Service.service;

import com.Evidence_Service.dto.ForensicInvestResultDTO;

import java.util.List;

public interface ForensicInvestResultService {
    ForensicInvestResultDTO addForensicInvestResult(String evidenceId, ForensicInvestResultDTO dto);

    ForensicInvestResultDTO getForensicInvestById(String id);

    List<ForensicInvestResultDTO> getAllForensicInvestByEvidenceId(String evidenceId);

    ForensicInvestResultDTO updateForensicInvest(String evidenceId, String resultId, ForensicInvestResultDTO dto);

    void deleteForensicInvest(String id);
}
