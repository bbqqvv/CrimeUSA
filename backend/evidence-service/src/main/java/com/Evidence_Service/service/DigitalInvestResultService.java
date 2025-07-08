package com.Evidence_Service.service;

import com.Evidence_Service.dto.DigitalInvestResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DigitalInvestResultService {
    DigitalInvestResultDTO addDigitalInvestResult(String evidenceId, DigitalInvestResultDTO dto);

    void assignDigitalInvestResult(String investigationPlanId, String uploadFile, String content);


    DigitalInvestResultDTO getDigitalInvestByResultId(String resultId);

    Page<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId, Pageable pageable);

    DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto);

    void deleteDigitalInvestByResultId(String resultId);

    boolean existsByEvidenceId(String evidenceId);

    void deleteByEvidenceId(String evidenceId);
}
