package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.FinancialInvestResultDTO;
import com.Evidence_Service.dto.event.caller.FinancialInvestResultCreatedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.KafkaEventPublisher;
import com.Evidence_Service.mapper.FinancialInvestResultMapper;
import com.Evidence_Service.model.FinancialInvestResult;
import com.Evidence_Service.repository.FinancialInvestResultRepository;
import com.Evidence_Service.service.FinancialInvestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialInvestResultServiceImpl implements FinancialInvestResultService {

    private final EvidenceServiceImpl evidenceService;
    private final FinancialInvestResultRepository financialInvestResultRepository;
    private final KafkaEventPublisher publisher;

    @Override
    public FinancialInvestResultDTO addFinancialInvestResult(String evidenceId, FinancialInvestResultDTO dto) {
        if (!evidenceService.existsByEvidenceId(evidenceId))
            throw  new AppException(ErrorCode.FINANCIAL_INVEST_RESULT_NOT_FOUND);

        FinancialInvestResult result = FinancialInvestResultMapper.toEntity(dto);
        result.setEvidenceId(evidenceId);

        result = financialInvestResultRepository.save(result);
        var resultDTO = FinancialInvestResultMapper.toDTO(result);
        publisher.send("digital-invest-result.created", FinancialInvestResultCreatedEvent.from(resultDTO));
        return FinancialInvestResultMapper.toDTO(result);
    }

    @Override
    public FinancialInvestResultDTO getFinancialInvestById(String id) {
        return financialInvestResultRepository.findById(id)
                .map(FinancialInvestResultMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.FINANCIAL_INVEST_RESULT_NOT_FOUND));
    }

    @Override
    public List<FinancialInvestResultDTO> getAllFinancialInvestByEvidenceId(String evidenceId) {
        return financialInvestResultRepository.findByEvidenceId(evidenceId)
                .stream().map(FinancialInvestResultMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public FinancialInvestResultDTO updateFinancialInvest(String evidenceId, String resultId, FinancialInvestResultDTO dto) {
        var entity = financialInvestResultRepository.findById(resultId)
                .orElseThrow(() -> new AppException(ErrorCode.FINANCIAL_INVEST_RESULT_NOT_FOUND));
        entity = FinancialInvestResultMapper.toEntity(dto);
        return FinancialInvestResultMapper.toDTO(financialInvestResultRepository.save(entity));
    }

    @Override
    public void deleteFinancialInvest(String id) {
        if (!financialInvestResultRepository.existsById(id))
            throw new AppException(ErrorCode.FINANCIAL_INVEST_RESULT_NOT_FOUND);
        financialInvestResultRepository.deleteById(id);
    }
}

