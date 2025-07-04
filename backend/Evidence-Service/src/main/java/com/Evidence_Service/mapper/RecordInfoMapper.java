package com.Evidence_Service.mapper;

import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.model.RecordInfo;
import org.springframework.stereotype.Component;

@Component
public class RecordInfoMapper {

    public RecordInfoDTO toDTO(RecordInfo entity) {
        if (entity == null) return null;
        return RecordInfoDTO.builder()
                .recordInfoId(entity.getRecordInfoId())
                .evidenceId(entity.getEvidenceId())
                .typeName(entity.getTypeName())
                .source(entity.getSource())
                .summary(entity.getSummary())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public RecordInfo toEntity(RecordInfoDTO dto) {
        if (dto == null) return null;
        return RecordInfo.builder()
                .recordInfoId(dto.getRecordInfoId())
                .evidenceId(dto.getEvidenceId())
                .typeName(dto.getTypeName())
                .source(dto.getSource())
                .summary(dto.getSummary())
                .build();
    }
}
