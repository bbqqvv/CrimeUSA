package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.DigitalInvestResultDTO;
import com.Evidence_Service.event.caller.DigitalInvestResultCreatedEvent;
import com.Evidence_Service.event.listener.ResultInvestAssignedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.EventPublisher;
import com.Evidence_Service.mapper.DigitalInvestResultMapper;
import com.Evidence_Service.entity.DigitalInvestResult;
import com.Evidence_Service.repository.DigitalInvestResultRepository;
import com.Evidence_Service.service.DigitalInvestResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DigitalInvestResultServiceImpl implements DigitalInvestResultService {

    private final EvidenceServiceImpl evidenceService;
    private final DigitalInvestResultRepository digitalInvestResultRepository;
    private final EventPublisher publisher;

    @Override
    @CacheEvict(value = {"digitalInvestResult", "digitalInvestList"}, allEntries = true)
    public DigitalInvestResultDTO addDigitalInvestResult(String evidenceId, DigitalInvestResultDTO dto) {
        try {
            if (digitalInvestResultRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId)) {
                throw new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND);
            }

            DigitalInvestResult result = DigitalInvestResultMapper.toEntity(dto);
            result.setEvidenceId(evidenceId);

            result = digitalInvestResultRepository.save(result);

            var resultDTO = DigitalInvestResultMapper.toDTO(result);

            // Publish Kafka event
            publisher.send("digital-invest-result.created", DigitalInvestResultCreatedEvent.from(resultDTO));

            return resultDTO;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while adding digital investigation result for evidenceId: {}", evidenceId, e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed to add digital investigation result");
        }
    }

    @CacheEvict(value = {"digitalInvestResult", "digitalInvestList"}, allEntries = true)
    @Override
    public void assignDigitalInvestResult(ResultInvestAssignedEvent event) {
        try {
            List<DigitalInvestResult> digitalInvestResults = digitalInvestResultRepository.findAllByInvestigationPlanIdAndIsDeletedFalse(event.getInvestigationPlanId());

            if (digitalInvestResults ==  null) {
                DigitalInvestResult.builder()
                        .evidenceId(event.getEvidenceId())
                        .result(event.getContent())
                        .uploadFile(event.getUploadFile())
                        .build();
            } else {
                for (DigitalInvestResult digitalInvestResult : digitalInvestResults) {
                    digitalInvestResult.setEvidenceId(event.getEvidenceId());
                    digitalInvestResult.setResult(event.getContent());
                    digitalInvestResult.setUploadFile(event.getUploadFile());
                    digitalInvestResultRepository.save(digitalInvestResult);
                }
            }
            log.info("Assigned Investigation");
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Cacheable(value = "digitalInvestResult", key = "#resultId")
    public DigitalInvestResultDTO getDigitalInvestByResultId(String resultId) {
        try {
            return DigitalInvestResultMapper.toDTO(
                    digitalInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                            .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND))
            );
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while retrieving digital investigation result by resultId: {}", resultId, e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed to get digital investigation result");
        }
    }

    @Override
    @Cacheable(value = "digitalInvestList", key = "#evidenceId + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<DigitalInvestResultDTO> getAllDigitalInvestByEvidenceId(String evidenceId, Pageable pageable) {
        try {
            return digitalInvestResultRepository.findAllByEvidenceIdAndIsDeletedFalse(evidenceId, pageable)
                    .map(DigitalInvestResultMapper::toDTO);
        } catch (Exception e) {
            log.error("Error while listing digital investigation results for evidenceId: {}", evidenceId, e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed to list digital investigation results");
        }
    }

    @Override
    @CacheEvict(value = {"digitalInvestResult", "digitalInvestList"}, allEntries = true)
    public DigitalInvestResultDTO updateDigitalInvest(String evidenceId, String resultId, DigitalInvestResultDTO dto) {
        try {
            var existing = digitalInvestResultRepository.findById(resultId)
                    .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));

            // Preserve existing resultId and evidenceId
            DigitalInvestResult updated = DigitalInvestResultMapper.toEntity(dto);
            updated.setResultId(existing.getResultId());
            updated.setEvidenceId(existing.getEvidenceId());

            return DigitalInvestResultMapper.toDTO(digitalInvestResultRepository.save(updated));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while updating digital investigation result with resultId: {}", resultId, e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed to update digital investigation result");
        }
    }

    @Override
    @CacheEvict(value = {"digitalInvestResult", "digitalInvestList"}, allEntries = true)
    public void deleteDigitalInvestByResultId(String resultId) {
        try {
            DigitalInvestResult digitalInvestResult = digitalInvestResultRepository.findByResultIdAndIsDeletedFalse(resultId)
                    .orElseThrow(() -> new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND));

            // Soft delete
            digitalInvestResult.setDeleted(true);
            digitalInvestResultRepository.save(digitalInvestResult);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while deleting digital investigation result with resultId: {}", resultId, e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed to delete digital investigation result");
        }
    }

    @Override
    public boolean existsByEvidenceId(String evidenceId) {
        try {
            return digitalInvestResultRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteByEvidenceId(String evidenceId) {
        try {
            List<DigitalInvestResult> digitalInvestResults = digitalInvestResultRepository.findAllByEvidenceIdAndIsDeletedFalse(evidenceId);

            if (digitalInvestResults == null) throw new AppException(ErrorCode.DIGITAL_INVEST_RESULT_NOT_FOUND);

            for (DigitalInvestResult digitalInvestResult : digitalInvestResults) {
                digitalInvestResult.setDeleted(true);
                digitalInvestResultRepository.save(digitalInvestResult);
            }
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
