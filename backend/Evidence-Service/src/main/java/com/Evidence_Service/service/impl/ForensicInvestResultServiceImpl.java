package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.ForensicInvestResultDTO;
import com.Evidence_Service.dto.event.caller.ForensicInvestResultCreatedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.KafkaEventPublisher;
import com.Evidence_Service.mapper.ForensicInvestResultMapper;
import com.Evidence_Service.model.ForensicInvestResult;
import com.Evidence_Service.repository.ForensicInvestResultRepository;
import com.Evidence_Service.service.ForensicInvestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForensicInvestResultServiceImpl implements ForensicInvestResultService {

    private final ForensicInvestResultRepository forensicInvestResultRepository;
    private final KafkaEventPublisher publisher;

    @Override
    public ForensicInvestResultDTO addForensicInvestResult(String evidenceId, ForensicInvestResultDTO dto) {
        if (!forensicInvestResultRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId))
            throw new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND);

        ForensicInvestResult result = ForensicInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = forensicInvestResultRepository.save(result);
        var resultDTO = ForensicInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", ForensicInvestResultCreatedEvent.from(resultDTO));
        return ForensicInvestResultMapper.toDTO(result);
    }

    @Override
    public ForensicInvestResultDTO getForensicInvestById(String resultId) {
        return forensicInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                .map(ForensicInvestResultMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND));
    }

    @Override
    public Page<ForensicInvestResultDTO> getAllForensicInvestByEvidenceId(String evidenceId, Pageable pageable) {
        return forensicInvestResultRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId, pageable)
                .map(ForensicInvestResultMapper::toDTO);
    }

    @Override
    public Page<ForensicInvestResultDTO> getAllForensicInvestByInvestigationId(String investigationId, Pageable pageable) {
        return forensicInvestResultRepository.findByInvestigationPlanIdAndIsDeletedFalse(investigationId, pageable)
                .map(ForensicInvestResultMapper::toDTO);
    }

    @Override
    public ForensicInvestResultDTO updateForensicInvest(String evidenceId, String resultId, ForensicInvestResultDTO dto) {
        var entity = forensicInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND));
        entity = ForensicInvestResultMapper.toEntity(dto);
        return ForensicInvestResultMapper.toDTO(forensicInvestResultRepository.save(entity));
    }

    @Override
    public void deleteForensicInvest(String resultId) {
         ForensicInvestResult forensicInvestResult = forensicInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                 .orElseThrow(() -> new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND));
        forensicInvestResult.setDeleted(true);
        forensicInvestResultRepository.save(forensicInvestResult);
    }
}

