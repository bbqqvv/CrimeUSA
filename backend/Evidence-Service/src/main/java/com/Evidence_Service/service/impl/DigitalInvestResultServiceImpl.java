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
        if(evidenceService.existedByEvidenceId(evidenceId))
            throw new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND);

        DigitalInvestResult result = DigitalInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = digitalInvestResultRepository.save(result);
        var resultDTO = DigitalInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", DigitalInvestResultCreatedEvent.from(resultDTO));
        return DigitalInvestResultMapper.toDTO(result);
    }

    @Override
    public DigitalInvestResultDTO getDigitalInvestById(String id) {
        return digitalInvestResultRepository.findById(id)
                .map(DigitalInvestResultMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));
    }

    @Override
    public List<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId) {
        return digitalInvestResultRepository.findByEvidenceId(evidenceId)
                .stream().map(DigitalInvestResultMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto) {
        var entity = digitalInvestResultRepository.findById(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));
        entity = DigitalInvestResultMapper.toEntity(dto);
        return DigitalInvestResultMapper.toDTO(digitalInvestResultRepository.save(entity));
    }

    @Override
    public void deleteDigitalInvest(String id) {
        if (!digitalInvestResultRepository.existsById(id))
            throw new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND);
        digitalInvestResultRepository.deleteById(id);
    }
}

