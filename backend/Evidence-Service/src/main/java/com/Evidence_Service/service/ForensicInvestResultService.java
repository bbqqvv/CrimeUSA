package com.Evidence_Service.service;

import com.Evidence_Service.dto.ForensicInvestResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ForensicInvestResultService {
    ForensicInvestResultDTO addForensicInvestResult(String evidenceId, ForensicInvestResultDTO dto);

    ForensicInvestResultDTO getForensicInvestById(String resultId);

    Page<ForensicInvestResultDTO> getAllForensicInvestByEvidenceId(String evidenceId, Pageable pageable);

    Page<ForensicInvestResultDTO> getAllForensicInvestByInvestigationId(String investigationId, Pageable pageable);

    ForensicInvestResultDTO updateForensicInvest(String evidenceId, String resultId, ForensicInvestResultDTO dto);

    void deleteForensicInvest(String resultId);
}
