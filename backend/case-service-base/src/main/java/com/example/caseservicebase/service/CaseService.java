package com.example.caseservicebase.service;

import com.backend.caseservice.dto.requestDTO.CaseRequestDTO;
import com.backend.caseservice.model.Case;

import java.util.List;

public interface CaseService {
    Long createCase(CaseRequestDTO request);
    Case updateCase(Long caseId, CaseRequestDTO request);
    Case getCaseById(Long caseId);
    List<Case> getAllCases();
    void softDeleteCase(Long caseId);
}
