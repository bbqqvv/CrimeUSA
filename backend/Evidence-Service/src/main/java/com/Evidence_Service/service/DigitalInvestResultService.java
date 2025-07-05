package com.Evidence_Service.service;

import com.Evidence_Service.dto.DigitalInvestResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DigitalInvestResultService {
    DigitalInvestResultDTO addDigitalInvestResult(String evidenceId, DigitalInvestResultDTO dto);

    DigitalInvestResultDTO getDigitalInvestByResultId(String resultId);

    Page<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId, Pageable pageable);

    DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto);

    void deleteDigitalInvest(String resultId);
}
