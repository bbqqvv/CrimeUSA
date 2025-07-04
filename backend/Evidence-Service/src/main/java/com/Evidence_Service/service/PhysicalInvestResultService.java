package com.Evidence_Service.service;

import com.Evidence_Service.dto.PhysicalInvestResultDTO;

import java.util.List;

public interface PhysicalInvestResultService {
    PhysicalInvestResultDTO addPhysicalInvestResult(String evidenceId, PhysicalInvestResultDTO dto);

    PhysicalInvestResultDTO getPhysicalInvestById(String id);

    List<PhysicalInvestResultDTO> getAllPhysicalInvestByEvidenceId(String evidenceId);

    PhysicalInvestResultDTO updatePhysicalInvest(String evidenceId, String resultId, PhysicalInvestResultDTO dto);

    void deletePhysicalInvest(String id);
}
