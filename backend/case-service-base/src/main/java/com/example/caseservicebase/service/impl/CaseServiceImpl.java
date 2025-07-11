package com.example.caseservicebase.service.impl;

import com.backend.caseservice.dto.requestDTO.CaseRequestDTO;
import com.backend.caseservice.model.Case;
import com.backend.caseservice.repository.CaseRepository;
import com.backend.caseservice.service.CaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;

    @Override
    public Long createCase(CaseRequestDTO request) {
        if (request.getCaseId() == null || request.getCaseId() <= 0) {
            throw new IllegalArgumentException("Case ID must be a positive non-null value");
        }
        Case caseEntity = Case.builder()
                .caseId(request.getCaseId())
                .caseNumber(request.getCaseNumber())
                .caseTarget(request.getCaseTarget())
                .severity(request.getSeverity())
                .status(request.getStatus())
                .summary(request.getSummary())
                .typeCase(request.getTypeCase())
                .isDeleted(false)
                .build();
        log.info("Create case successfully, caseId={}", caseEntity.getCaseId());
        return caseRepository.save(caseEntity).getCaseId();
    }

    @Override
    public Case updateCase(Long caseId, CaseRequestDTO request) {
        if (caseId == null || caseId <= 0) {
            throw new IllegalArgumentException("Case ID must be a positive non-null value");
        }
        Case caseEntity = caseRepository.findByIdAndIsDeletedFalse(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found with id = " + caseId));
        if (request.getCaseNumber() != null) caseEntity.setCaseNumber(request.getCaseNumber());
        if (request.getCaseTarget() != null) caseEntity.setCaseTarget(request.getCaseTarget());
        if (request.getSeverity() != null) caseEntity.setSeverity(request.getSeverity());
        if (request.getStatus() != null) caseEntity.setStatus(request.getStatus());
        if (request.getSummary() != null) caseEntity.setSummary(request.getSummary());
        if (request.getTypeCase() != null) caseEntity.setTypeCase(request.getTypeCase());
        log.info("Case has updated successfully, caseId={}", caseId);
        return caseRepository.save(caseEntity);
    }

    @Override
    public Case getCaseById(Long caseId) {
        if (caseId == null || caseId <= 0) {
            throw new IllegalArgumentException("Case ID must be a positive non-null value");
        }
        return caseRepository.findByIdAndIsDeletedFalse(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found with id = " + caseId));
    }

    @Override
    public List<Case> getAllCases() {
        return caseRepository.findAllByIsDeletedFalse();
    }

    @Override
    public void softDeleteCase(Long caseId) {
        if (caseId == null || caseId <= 0) {
            throw new IllegalArgumentException("Case ID must be a positive non-null value");
        }
        Case caseEntity = caseRepository.findByIdAndIsDeletedFalse(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found with id = " + caseId));
        caseEntity.setIsDeleted(true);
        caseRepository.save(caseEntity);
        log.info("Soft deleted case successfully, caseId={}", caseId);
    }

}
