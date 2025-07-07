package com.backend.investigationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.investigationservice.dto.request.InvestigationPlanCreationRequest;
import com.backend.investigationservice.dto.response.InvestigationPlanResponse;

import java.util.List;
import java.util.UUID;

public interface InvestigationPlanService {
    List<InvestigationPlanResponse> findAll();
    Page<InvestigationPlanResponse> findAll(String keyword, Pageable pageable);
    InvestigationPlanResponse createPlan(InvestigationPlanCreationRequest request);
    List<InvestigationPlanResponse> getByCaseId(UUID caseId);
    InvestigationPlanResponse updatePlan(UUID id, InvestigationPlanCreationRequest request);
    InvestigationPlanResponse deletePlan(UUID id, InvestigationPlanCreationRequest request);
}
