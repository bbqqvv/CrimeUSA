package com.Evidence_Service.mapper;

import com.Evidence_Service.dto.ForensicInvestResultDTO;
import com.Evidence_Service.model.ForensicInvestResult;

public class ForensicInvestResultMapper {

    public static ForensicInvestResultDTO toDTO(ForensicInvestResult entity) {
        if (entity == null) return null;

        return ForensicInvestResultDTO.builder()
                .resultId(entity.getResultId())
                .investigationPlanId(entity.getInvestigationPlanId())
                .evidenceId(entity.getEvidenceId())
                .labName(entity.getLabName())
                .report(entity.getReport())
                .resultSummary(entity.getResultSummary())
                .createdAt(entity.getCreatedAt())
                .isDeleted(entity.isDeleted())
                .build();
    }

    public static ForensicInvestResult toEntity(ForensicInvestResultDTO dto) {
        if (dto == null) return null;

        return ForensicInvestResult.builder()
                .resultId(dto.getResultId())
                .investigationPlanId(dto.getInvestigationPlanId())
                .evidenceId(dto.getEvidenceId())
                .labName(dto.getLabName())
                .report(dto.getReport())
                .resultSummary(dto.getResultSummary())
                .createdAt(dto.getCreatedAt())
                .isDeleted(dto.isDeleted())
                .build();
    }
}
