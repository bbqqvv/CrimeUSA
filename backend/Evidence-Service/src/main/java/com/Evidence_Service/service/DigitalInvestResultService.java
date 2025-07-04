package com.Evidence_Service.service;

import com.Evidence_Service.dto.DigitalInvestResultDTO;

import java.util.List;

public interface DigitalInvestResultService {
    DigitalInvestResultDTO addDigitalInvestResult(String evidenceId, DigitalInvestResultDTO dto);

    DigitalInvestResultDTO getDigitalInvestById(String id);

    List<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId);

    DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto);

    void deleteDigitalInvest(String id);
}
