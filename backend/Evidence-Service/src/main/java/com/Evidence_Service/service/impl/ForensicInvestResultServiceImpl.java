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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForensicInvestResultServiceImpl implements ForensicInvestResultService {

    private final EvidenceServiceImpl evidenceService;
    private final ForensicInvestResultRepository forensicInvestResultRepository;
    private final KafkaEventPublisher publisher;

    @Override
    public ForensicInvestResultDTO addForensicInvestResult(String evidenceId, ForensicInvestResultDTO dto) {
        if (!evidenceService.existsByEvidenceId(evidenceId))
            throw new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND);

        ForensicInvestResult result = ForensicInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = forensicInvestResultRepository.save(result);
        var resultDTO = ForensicInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", ForensicInvestResultCreatedEvent.from(resultDTO));
        return ForensicInvestResultMapper.toDTO(result);
    }

    @Override
    public ForensicInvestResultDTO getForensicInvestById(String id) {
        return forensicInvestResultRepository.findById(id)
                .map(ForensicInvestResultMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND));
    }

    @Override
    public List<ForensicInvestResultDTO> getAllForensicInvestByEvidenceId(String evidenceId) {
        return forensicInvestResultRepository.findByEvidenceId(evidenceId)
                .stream().map(ForensicInvestResultMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ForensicInvestResultDTO updateForensicInvest(String evidenceId, String resultId, ForensicInvestResultDTO dto) {
        var entity = forensicInvestResultRepository.findById(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND));
        entity = ForensicInvestResultMapper.toEntity(dto);
        return ForensicInvestResultMapper.toDTO(forensicInvestResultRepository.save(entity));
    }

    @Override
    public void deleteForensicInvest(String id) {
        if (!forensicInvestResultRepository.existsById(id))
            throw new AppException(ErrorCode.FORENSIC_INVEST_RESULT_NOT_FOUND);
        forensicInvestResultRepository.deleteById(id);
    }
}

