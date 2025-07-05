package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.PhysicalInvestResultDTO;
import com.Evidence_Service.dto.event.caller.PhysicalInvestResultCreatedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.KafkaEventPublisher;
import com.Evidence_Service.mapper.PhysicalInvestResultMapper;
import com.Evidence_Service.model.PhysicalInvestResult;
import com.Evidence_Service.repository.PhysicalInvestResultRepository;
import com.Evidence_Service.service.PhysicalInvestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhysicalInvestResultServiceImpl implements PhysicalInvestResultService {

    private final EvidenceServiceImpl evidenceService;
    private final PhysicalInvestResultRepository physicalInvestResultRepository;
    private final KafkaEventPublisher publisher;

    @Override
    public PhysicalInvestResultDTO addPhysicalInvestResult(String evidenceId, PhysicalInvestResultDTO dto) {
        if (!evidenceService.existsByEvidenceId(evidenceId))
            throw  new AppException(ErrorCode.PHYSICAL_INVEST_RESULT_NOT_FOUND);

        PhysicalInvestResult result = PhysicalInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = physicalInvestResultRepository.save(result);
        var resultDTO = PhysicalInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", PhysicalInvestResultCreatedEvent.from(resultDTO));
        return PhysicalInvestResultMapper.toDTO(result);
    }

    @Override
    public PhysicalInvestResultDTO getPhysicalInvestByResultId(String resultId) {
        return physicalInvestResultRepository.findById(resultId)
                .map(PhysicalInvestResultMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.PHYSICAL_INVEST_RESULT_NOT_FOUND));
    }

    @Override
    public Page<PhysicalInvestResultDTO> getAllPhysicalInvestByEvidenceId(String evidenceId, Pageable pageable) {
        return physicalInvestResultRepository.findByEvidenceId(evidenceId, pageable)
                .map(PhysicalInvestResultMapper::toDTO);
    }

    @Override
    public Page<PhysicalInvestResultDTO> getAllPhysicalInvestByInvestigationId(String investigationId, Pageable pageable) {
        return null;
    }

    @Override
    public PhysicalInvestResultDTO updatePhysicalInvest(String evidenceId, String resultId, PhysicalInvestResultDTO dto) {
        var entity = physicalInvestResultRepository.findById(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.PHYSICAL_INVEST_RESULT_NOT_FOUND));
        entity = PhysicalInvestResultMapper.toEntity(dto);
        return PhysicalInvestResultMapper.toDTO(physicalInvestResultRepository.save(entity));
    }

    @Override
    public void deletePhysicalInvest(String resultId) {
        PhysicalInvestResult physicalInvestResult = physicalInvestResultRepository.findByResultId(resultId);
        if (physicalInvestResult == null)
            throw new AppException(ErrorCode.PHYSICAL_INVEST_RESULT_NOT_FOUND);
        physicalInvestResult.setDeleted(true);
        physicalInvestResultRepository.save(physicalInvestResult);
    }
}

