package com.Evidence_Service.service;

import com.Evidence_Service.dto.ForensicInvestResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ForensicInvestResultService {
    ForensicInvestResultDTO addForensicInvestResult(String evidenceId, ForensicInvestResultDTO dto);

    void assignForensicInvestResult(String investigationPlanId, String uploadFile, String content);

    ForensicInvestResultDTO getForensicInvestById(String resultId);

    Page<ForensicInvestResultDTO> getAllForensicInvestByEvidenceId(String evidenceId, Pageable pageable);

    Page<ForensicInvestResultDTO> getAllForensicInvestByInvestigationId(String investigationId, Pageable pageable);

    ForensicInvestResultDTO updateForensicInvest(String evidenceId, String resultId, ForensicInvestResultDTO dto);

    void deleteForensicInvestByResultId(String resultId);

    boolean existsByEvidenceId(String evidenceId);

    void deleteByEvidenceId(String evidenceId);
}
