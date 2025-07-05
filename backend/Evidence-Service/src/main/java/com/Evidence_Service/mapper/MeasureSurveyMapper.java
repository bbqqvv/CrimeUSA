package com.Evidence_Service.mapper;

import com.Evidence_Service.dto.MeasureSurveyDTO;
import com.Evidence_Service.dto.PhysicalInvestResultDTO;
import com.Evidence_Service.model.MeasureSurvey;
import com.Evidence_Service.model.PhysicalInvestResult;
import com.Evidence_Service.model.PhysicalInvestStatus;
import org.springframework.stereotype.Component;

@Component
public class MeasureSurveyMapper {

    public static MeasureSurveyDTO toDTO(MeasureSurveyDTO entity) {
        if (entity == null) return null;

        return MeasureSurveyDTO.builder()
                .resultId(entity.getResultId())
                .measureSurveyId(entity.getMeasureSurveyId())
                .source(entity.getSource())
                .typeName(entity.getTypeName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .isDeleted(entity.getIsDeleted())
                .build();
    }

    public static MeasureSurvey toEntity(MeasureSurvey dto) {
        if (dto == null) return null;

        return MeasureSurvey.builder()
                .resultId(dto.getResultId())
                .measureSurveyId(dto.getMeasureSurveyId())
                .source(dto.getSource())
                .typeName(dto.getTypeName())
                .updatedAt(dto.getUpdatedAt())
                .createdAt(dto.getCreatedAt())
                .isDeleted(dto.isDeleted())
                .build();
    }
}
