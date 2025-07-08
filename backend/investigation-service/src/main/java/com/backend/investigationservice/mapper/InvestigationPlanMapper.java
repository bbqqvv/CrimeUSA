package com.backend.investigationservice.mapper;

import com.backend.investigationservice.dto.request.InvestigationPlanCreationRequest;
import com.backend.investigationservice.dto.response.InvestigationPlanResponse;
import com.backend.investigationservice.model.InvestigationPlan;

import java.util.UUID;

public class InvestigationPlanMapper {

    public static InvestigationPlan toEntity(InvestigationPlanCreationRequest request) {
        if (request == null) return null;

        InvestigationPlan plan = new InvestigationPlan();
        plan.setSummary(request.getSummary());
        plan.setCreateAt(request.getCreateAt());
        plan.setDeadlineDate(request.getDeadlineDate());
        plan.setStatus(request.getStatus());
        plan.setPlanContent(request.getPlanContent());
        plan.setType(request.getType());
        plan.setHolidayConflict(request.getHolidayConflict());
        plan.setCreatedOfficerName(request.getCreatedOfficerName());
        plan.setAcceptedOfficerName(request.getAcceptedOfficerName());

        // Parse String caseId to UUID
        plan.setCaseId(UUID.fromString(request.getCaseId()));

        // Optional: set default values
        plan.setDeleted(false); // Default not deleted
        return plan;
    }

    public static InvestigationPlanResponse toResponse(InvestigationPlan plan) {
        if (plan == null) return null;

        return InvestigationPlanResponse.builder()
                .investigationPlanId(plan.getInvestigationPlanId())
                .summary(plan.getSummary())
                .createAt(plan.getCreateAt())
                .deadlineDate(plan.getDeadlineDate())
                .status(plan.getStatus())
                .planContent(plan.getPlanContent())
                .type(plan.getType())
                .holidayConflict(plan.getHolidayConflict())
                .createdOfficerName(plan.getCreatedOfficerName())
                .acceptedOfficerName(plan.getAcceptedOfficerName())
                .caseId(plan.getCaseId())
                .build();
    }
}