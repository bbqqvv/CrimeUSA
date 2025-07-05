package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.DigitalInvestResultDTO;
import com.Evidence_Service.dto.event.caller.DigitalInvestResultCreatedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.EventPublisher;
import com.Evidence_Service.mapper.DigitalInvestResultMapper;
import com.Evidence_Service.model.DigitalInvestResult;
import com.Evidence_Service.repository.DigitalInvestResultRepository;
import com.Evidence_Service.service.DigitalInvestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DigitalInvestResultServiceImpl implements DigitalInvestResultService {

    private final EvidenceServiceImpl evidenceService;
    private final DigitalInvestResultRepository digitalInvestResultRepository;
    private final EventPublisher publisher;

    @Override
    public DigitalInvestResultDTO addDigitalInvestResult(String evidenceId, DigitalInvestResultDTO dto) {
        if(digitalInvestResultRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId))
            throw new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND);

        DigitalInvestResult result = DigitalInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = digitalInvestResultRepository.save(result);
        var resultDTO = DigitalInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", DigitalInvestResultCreatedEvent.from(resultDTO));
        return DigitalInvestResultMapper.toDTO(result);
    }

    @Override
    public DigitalInvestResultDTO getDigitalInvestByResultId(String resultId) {
        return DigitalInvestResultMapper.toDTO(
                digitalInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                        .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND))
                );

    }

    @Override
    public Page<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId, Pageable pageable) {
        return digitalInvestResultRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId, pageable)
                .map(DigitalInvestResultMapper::toDTO);
    }

    @Override
    public DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto) {
        var entity = digitalInvestResultRepository.findById(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));
        entity = DigitalInvestResultMapper.toEntity(dto);
        return DigitalInvestResultMapper.toDTO(digitalInvestResultRepository.save(entity));
    }

    @Override
    public void deleteDigitalInvest(String resultId) {
        DigitalInvestResult digitalInvestResult = digitalInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));
        digitalInvestResult.setDeleted(true);
        digitalInvestResultRepository.save(digitalInvestResult);
    }
}

