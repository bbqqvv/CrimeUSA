package com.Evidence_Service.service.impl;

import com.Evidence_Service.client.ReportClient;
import com.Evidence_Service.client.CaseClient;
import com.Evidence_Service.client.SuspectClient;
import com.Evidence_Service.client.WarrantClient;
import com.Evidence_Service.dto.*;
import com.Evidence_Service.event.caller.EvidenceCreatedEvent;
import com.Evidence_Service.event.caller.EvidenceDeletedEvent;
import com.Evidence_Service.event.listener.AnalysisResultEvent;
import com.Evidence_Service.event.listener.CaseAssignedEvent;
import com.Evidence_Service.event.listener.SuspectAssignedEvent;
import com.Evidence_Service.event.listener.WarrantAssignedEvent;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.EventPublisher;
import com.Evidence_Service.mapper.EvidenceMapper;
import com.Evidence_Service.entity.*;
import com.Evidence_Service.repository.*;
import com.Evidence_Service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import static com.Evidence_Service.mapper.EvidenceMapper.toDTO;
import static com.Evidence_Service.mapper.EvidenceMapper.toEntity;

@Service
@RequiredArgsConstructor
public class EvidenceServiceImpl implements EvidenceService {

    private static final Logger log = LoggerFactory.getLogger(EvidenceServiceImpl.class);

    private final EvidenceRepository evidenceRepository;
    private final WarrantEvidenceRepository warrantEvidenceRepository;
    private final SuspectEvidenceRepository suspectEvidenceRepository;
    private final CaseEvidenceRepository caseEvidenceRepository;
    private final ReportEvidenceRepository reportEvidenceRepository;
    private final ReportClient reportClient;
    private final CaseClient caseClient;
    private final WarrantClient warrantClient;
    private final SuspectClient suspectClient;
    private final EventPublisher eventPublisher;
    private final DigitalInvestResultService digitalInvestResultService;
    private final FinancialInvestResultService financialInvestResultService;
    private final PhysicalInvestResultService physicalInvestResultService;
    private final ForensicInvestResultService forensicInvestResultService;
    private final RecordInfoService recordInfoService;

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO createEvidence(EvidenceDTO dto) {
        try {
            log.info("Starting evidence creation with data: {}", dto);
            // Convert DTO to entity for database storage
            Evidence entity = toEntity(dto);
            Evidence saved = evidenceRepository.save(entity);
            // Publish event to notify evidence creation
            eventPublisher.send("evidence.created", EvidenceMapper.toCreatedEvent(saved));
            log.info("Successfully created evidence with ID: {}", saved.getEvidenceId());
            return toDTO(saved);
        } catch (Exception ex) {
            log.error("Failed to create evidence: {}", ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public void deleteByEvidenceId(String evidenceId) {
        try {
            log.info("Starting deletion of evidence with ID: {}", evidenceId);
            Evidence evidence = getEvidenceOrThrow(evidenceId);
            evidence.setDeleted(true);
            evidence.setStatus(EvidenceStatus.DELETED);

            //Call delete result invest of Evidence if exists
            if (digitalInvestResultService.existsByEvidenceId(evidenceId)) {
                digitalInvestResultService.deleteByEvidenceId(evidenceId);
            }
            if (financialInvestResultService.existsByEvidenceId(evidenceId)) {
                financialInvestResultService.deleteByEvidenceId(evidenceId);
            }
            if (physicalInvestResultService.existsByEvidenceId(evidenceId)) {
                physicalInvestResultService.deleteByEvidenceId(evidenceId);
            }
            if (forensicInvestResultService.existsByEvidenceId(evidenceId)) {
                forensicInvestResultService.deleteByEvidenceId(evidenceId);
            }

            //Call delete RecordInfo of Evidence if exists
            if (recordInfoService.existsByEvidenceId(evidenceId)) {
                recordInfoService.deleteByEvidenceId(evidenceId);
            }

            evidenceRepository.save(evidence);
            // Publish event to notify evidence deletion
            eventPublisher.send("evidence.deleted", new EvidenceDeletedEvent());
            log.info("Successfully deleted evidence with ID: {}", evidenceId);
        } catch (Exception ex) {
            log.error("Failed to delete evidence with ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignCase(String evidenceId, CaseDTO dto) {
        try {
            log.info("Assigning case {} to evidence {}", dto.getCaseId(), evidenceId);
            Evidence evidence = getEvidenceOrThrow(evidenceId);
            evidence.setCaseId(dto.getCaseId());
            evidence.setStatus(EvidenceStatus.ASSIGNED);
            evidenceRepository.save(evidence);
            // Publish event to notify case assignment
            eventPublisher.send("case.assigned", new CaseAssignedEvent(evidenceId, dto.getCaseId(), LocalDateTime.now()));
            log.info("Successfully assigned case {} to evidence {}", dto.getCaseId(), evidenceId);
            return toDTO(evidence);
        } catch (AppException ae) {
            log.warn("Application exception during case assignment: {}", ae.getErrorCode());
            throw ae;
        } catch (Exception ex) {
            log.error("Failed to assign case to evidence {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignSuspect(String evidenceId, SuspectDTO dto) {
        try {
            log.info("Assigning suspect {} to evidence {}", dto.getSuspectId(), evidenceId);
            Evidence evidence = getEvidenceOrThrow(evidenceId);
            // Create relationship between evidence and suspect
            SuspectEvidence se = new SuspectEvidence();
            se.setEvidenceId(evidenceId);
            se.setSuspectId(dto.getSuspectId());
            suspectEvidenceRepository.save(se);
            // Publish event to notify suspect assignment
            eventPublisher.send("suspect.assigned", new SuspectAssignedEvent(evidenceId, dto.getSuspectId(), LocalDateTime.now()));
            log.info("Successfully assigned suspect {} to evidence {}", dto.getSuspectId(), evidenceId);
            return toDTO(evidence);
        } catch (Exception ex) {
            log.error("Failed to assign suspect to evidence {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignWarrant(String evidenceId, WarrantDTO dto) {
        try {
            log.info("Assigning warrant {} to evidence {}", dto.getWarrantId(), evidenceId);
            Evidence evidence = getEvidenceOrThrow(evidenceId);
            // Create relationship between evidence and warrant
            WarrantEvidence we = new WarrantEvidence();
            we.setEvidenceId(evidenceId);
            we.setWarrantId(dto.getWarrantId());
            warrantEvidenceRepository.save(we);
            // Publish event to notify warrant assignment
            eventPublisher.send("warrant.assigned", new WarrantAssignedEvent(evidenceId, dto.getWarrantId(), LocalDateTime.now()));
            log.info("Successfully assigned warrant {} to evidence {}", dto.getWarrantId(), evidenceId);
            return toDTO(evidence);
        } catch (Exception ex) {
            log.error("Failed to assign warrant to evidence {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO updateEvidence(EvidenceDTO dto) {
        try {
            log.info("Updating evidence with ID: {}", dto.getEvidenceId());
            Evidence entity = getEvidenceOrThrow(dto.getEvidenceId());
            entity.setDescription(dto.getDescription());
            Evidence saved = evidenceRepository.save(entity);
            log.info("Successfully updated evidence with ID: {}", dto.getEvidenceId());
            return toDTO(saved);
        } catch (Exception ex) {
            log.error("Failed to update evidence with ID {}: {}", dto.getEvidenceId(), ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(value = "evidence", key = "#evidenceId")
    @Override
    public EvidenceDTO getByEvidenceId(String evidenceId) {
        try {
            log.info("Retrieving evidence with ID: {}", evidenceId);
            EvidenceDTO result = EvidenceMapper.toDTO(getEvidenceOrThrow(evidenceId));
            log.info("Successfully retrieved evidence with ID: {}", evidenceId);
            return result;
        } catch (AppException ae) {
            log.warn("Evidence not found with ID: {}", evidenceId);
            throw ae;
        } catch (Exception ex) {
            log.error("Error retrieving evidence with ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean existsByEvidenceId(String evidenceId) {
        try {
            log.info("Checking if evidence exists with ID: {}", evidenceId);
            boolean exists = !evidenceRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId);
            log.info("Evidence existence check result for ID {}: {}", evidenceId, exists);
            return exists;
        } catch (Exception ex) {
            log.error("Error checking evidence existence with ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteByReportId(String reportId) {
        try {
            log.info("Deleting evidence by report ID: {}", reportId);
            // Find all evidence associated with the report
            List<ReportEvidence> reportEvidences = reportEvidenceRepository.findByReportIdAndIsDeletedFalse(reportId);
            reportEvidences.forEach(reportEvidence -> reportEvidence.setDeleted(true));
            reportEvidenceRepository.saveAll(reportEvidences);
            log.info("Successfully deleted evidence for report ID: {}", reportId);
        } catch (Exception ex) {
            log.error("Failed to delete evidence by report ID {}: {}", reportId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteByCaseId(String caseId) {
        try {
            log.info("Deleting evidence by case ID: {}", caseId);
            // Find all evidence associated with the case
            List<CaseEvidence> caseEvidences = caseEvidenceRepository.findByCaseIdAndIsDeletedFalse(caseId);
            caseEvidences.forEach(caseEvidence -> caseEvidence.setDeleted(true));
            caseEvidenceRepository.saveAll(caseEvidences);
            log.info("Successfully deleted evidence for case ID: {}", caseId);
        } catch (Exception ex) {
            log.error("Failed to delete evidence by case ID {}: {}", caseId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteByWarrantId(String warrantId) {
        try {
            log.info("Deleting evidence by warrant ID: {}", warrantId);
            // Find all evidence associated with the warrant
            List<WarrantEvidence> warrantEvidences = warrantEvidenceRepository.findByWarrantIdAndIsDeletedFalse(warrantId);
            warrantEvidences.forEach(warrantEvidence -> warrantEvidence.setDeleted(true));
            warrantEvidenceRepository.saveAll(warrantEvidences);
            log.info("Successfully deleted evidence for warrant ID: {}", warrantId);
        } catch (Exception ex) {
            log.error("Failed to delete evidence by warrant ID {}: {}", warrantId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteBySuspectId(String suspectId) {
        try {
            log.info("Deleting evidence by suspect ID: {}", suspectId);
            // Find all evidence associated with the suspect
            List<SuspectEvidence> suspectEvidences = suspectEvidenceRepository.findBySuspectIdAndIsDeletedFalse(suspectId);
            suspectEvidences.forEach(suspectEvidence -> suspectEvidence.setDeleted(true));
            suspectEvidenceRepository.saveAll(suspectEvidences);
            log.info("Successfully deleted evidence for suspect ID: {}", suspectId);
        } catch (Exception ex) {
            log.error("Failed to delete evidence by suspect ID {}: {}", suspectId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Cacheable(value = "evidences", key = "#evidenceIds")
    public Page<EvidenceDTO> getAllEvidence(Pageable pageable) {
        try {
            log.info("Retrieving all evidence with pagination");
            Page<EvidenceDTO> result = evidenceRepository.findAllNotDeleted(pageable)
                    .map(EvidenceMapper::toDTO);
            log.info("Successfully retrieved {} evidence items", result.getTotalElements());
            return result;
        } catch (Exception ex) {
            log.error("Failed to retrieve all evidence: {}", ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private Evidence getEvidenceOrThrow(String evidenceId) {
        log.info("Checking evidence existence for ID: {}", evidenceId);
        return evidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .orElseThrow(() -> {
                    log.warn("Evidence not found: {}", evidenceId);
                    return new AppException(ErrorCode.EVIDENCE_NOT_FOUND);
                });
    }

    @Override
    public List<SuspectDTO> getSuspectsByEvidence(String evidenceId) {
        try {
            log.info("Retrieving suspects for evidence ID: {}", evidenceId);
            // Get list of suspect IDs associated with evidence
            List<String> suspectIds = suspectEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                    .stream()
                    .map(SuspectEvidence::getSuspectId)
                    .toList();
            List<SuspectDTO> result = suspectClient.getSuspectByIds(suspectIds);
            log.info("Successfully retrieved {} suspects for evidence ID: {}", result.size(), evidenceId);
            return result;
        } catch (Exception ex) {
            log.error("Failed to retrieve suspects for evidence ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<WarrantDTO> getWarrantsByEvidence(String evidenceId) {
        try {
            log.info("Retrieving warrants for evidence ID: {}", evidenceId);
            // Get list of warrant IDs associated with evidence
            List<String> warrantIds = warrantEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                    .stream()
                    .map(WarrantEvidence::getWarrantId)
                    .toList();
            List<WarrantDTO> result = warrantClient.getWarrantByIds(warrantIds);
            log.info("Successfully retrieved {} warrants for evidence ID: {}", result.size(), evidenceId);
            return result;
        } catch (Exception ex) {
            log.error("Failed to retrieve warrants for evidence ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CaseDTO> getCasesByEvidence(String evidenceId) {
        try {
            log.info("Retrieving cases for evidence ID: {}", evidenceId);
            // Get list of case IDs associated with evidence
            List<String> caseIds = caseEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                    .stream()
                    .map(CaseEvidence::getCaseId)
                    .toList();
            List<CaseDTO> result = caseClient.getCasesByIds(caseIds);
            log.info("Successfully retrieved {} cases for evidence ID: {}", result.size(), evidenceId);
            return result;
        } catch (Exception ex) {
            log.error("Failed to retrieve cases for evidence ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ReportDTO> getReportsByEvidence(String evidenceId) {
        try {
            log.info("Retrieving reports for evidence ID: {}", evidenceId);
            // Get list of report IDs associated with evidence
            List<String> reportIds = reportEvidenceRepository.findByReportIdAndIsDeletedFalse(evidenceId)
                    .stream()
                    .map(ReportEvidence::getReportId)
                    .toList();
            List<ReportDTO> result = reportClient.getReportsByIds(reportIds);
            log.info("Successfully retrieved {} reports for evidence ID: {}", result.size(), evidenceId);
            return result;
        } catch (Exception ex) {
            log.error("Failed to retrieve reports for evidence ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void removeSuspectFromEvidence(String suspectId) {
        try {
            log.info("Removing suspect {} from evidence", suspectId);
            // Find and mark suspect-evidence relationships as deleted
            List<SuspectEvidence> suspectEvidenceList = suspectEvidenceRepository.findBySuspectIdAndIsDeletedFalse(suspectId);
            suspectEvidenceList.forEach(suspectEvidence -> {
                suspectEvidence.setDeleted(true);
                suspectEvidence.setDetachedAt(LocalDateTime.now());
            });
            suspectEvidenceRepository.saveAll(suspectEvidenceList);
            log.info("Successfully removed suspect {} from evidence", suspectId);
        } catch (Exception ex) {
            log.error("Failed to remove suspect {} from evidence: {}", suspectId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void saveEvidenceFromEvent(EvidenceCreatedEvent event) {
        try {
            log.info("Saving evidence from event with ID: {}", event.getEvidenceId());
            // Create new evidence entity from event data
            Evidence entity = Evidence.builder()
                    .evidenceId(event.getEvidenceId())
                    .status(EvidenceStatus.APPROVED)
                    .currentLocation(event.getLocation())
                    .collectorUsername(event.getCollector_username())
                    .build();
            evidenceRepository.save(entity);
            log.info("Successfully saved evidence from event with ID: {}", event.getEvidenceId());
        } catch (Exception ex) {
            log.error("Failed to save evidence from event with ID {}: {}", event.getEvidenceId(), ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateAnalysisResult(AnalysisResultEvent event) {
        try {
            log.info("Updating analysis result for evidence ID: {}", event.getEvidenceId());
            Evidence evidence = getEvidenceOrThrow(event.getEvidenceId());
            evidence.setMeasureSurveyId(event.getMeasureSurveyId());
            evidenceRepository.save(evidence);
            log.info("Successfully updated analysis result for evidence ID: {}", event.getEvidenceId());
        } catch (Exception ex) {
            log.error("Failed to update analysis result for evidence ID {}: {}", event.getEvidenceId(), ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void assignSuspectToEvidence(String evidenceId, String suspectId) {
        try {
            SuspectEvidence suspectEvidence = new SuspectEvidence();
            suspectEvidence.setEvidenceId(evidenceId);
            suspectEvidence.setSuspectId(suspectId);
            suspectEvidenceRepository.save(suspectEvidence);
        } catch (Exception ex) {
            log.error("Failed to assign suspect {} to evidence {}: {}", suspectId, evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void assignCaseToEvidence(String evidenceId, String caseId) {
        try {
            CaseEvidence caseEvidence = new CaseEvidence();
            caseEvidence.setEvidenceId(evidenceId);
            caseEvidence.setCaseId(caseId);
            caseEvidenceRepository.save(caseEvidence);
        } catch (Exception ex) {
            log.error("Failed to assign case {} to evidence {}: {}", caseId, evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void assignWarrantToEvidence(String evidenceId, String warrantId) {
        try {
            WarrantEvidence warrantEvidence = new WarrantEvidence();
            warrantEvidence.setEvidenceId(evidenceId);
            warrantEvidence.setWarrantId(warrantId);
            warrantEvidenceRepository.save(warrantEvidence);
        } catch (Exception ex) {
            log.error("Failed to assign warrant {} to evidence {}: {}", warrantId, evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void assignReportToEvidence(String evidenceId, String reportId) {
        try {
            log.info("Assigning report {} to evidence {}", reportId, evidenceId);
            ReportEvidence reportEvidence = new ReportEvidence();
            reportEvidence.setEvidenceId(evidenceId);
            reportEvidence.setReportId(reportId);
            reportEvidenceRepository.save(reportEvidence);
            log.info("Successfully assigned report {} to evidence {}", reportId, evidenceId);
        } catch (Exception ex) {
            log.error("Failed to assign report {} to evidence {}: {}", reportId, evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void softDeleteByMeasureSurveyId(String measureSurveyId) {
        try {
            log.info("Soft deleting evidence by measure survey ID: {}", measureSurveyId);
            // Find and mark all evidence with given measure survey ID as deleted
            List<Evidence> evidences = evidenceRepository.findAllByMeasureSurveyIdAndIsDeletedFalse(measureSurveyId);
            for (Evidence evidence : evidences) {
                evidence.setDeleted(true);
            }
            evidenceRepository.saveAll(evidences);
            log.info("Successfully soft deleted evidence for measure survey ID: {}", measureSurveyId);
        } catch (Exception ex) {
            log.error("Failed to soft delete evidence by measure survey ID {}: {}", measureSurveyId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean existsByReportId(String reportId) {
        try {
            log.info("Checking if evidence exists for report ID: {}", reportId);
            boolean exists = reportEvidenceRepository.existsByReportIdAndIsDeletedFalse(reportId);
            log.info("Evidence existence check for report ID {}: {}", reportId, exists);
            return exists;
        } catch (Exception ex) {
            log.error("Failed to check evidence existence for report ID {}: {}", reportId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean existsByCaseId(String caseId) {
        try {
            log.info("Checking if evidence exists for case ID: {}", caseId);
            boolean exists = caseEvidenceRepository.existsByCaseIdAndIsDeletedFalse(caseId);
            log.info("Evidence existence check for case ID {}: {}", caseId, exists);
            return exists;
        } catch (Exception ex) {
            log.error("Failed to check evidence existence for case ID {}: {}", caseId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean existsBySuspectId(String suspectId) {
        try {
            log.info("Checking if evidence exists for suspect ID: {}", suspectId);
            boolean exists = suspectEvidenceRepository.existsBySuspectIdAndIsDeletedFalse(suspectId);
            log.info("Evidence existence check for suspect ID {}: {}", suspectId, exists);
            return exists;
        } catch (Exception ex) {
            log.error("Failed to check evidence existence for suspect ID {}: {}", suspectId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean existsByWarrantId(String warrantId) {
        try {
            log.info("Checking if evidence exists for warrant ID: {}", warrantId);
            boolean exists = warrantEvidenceRepository.existsByWarrantIdAndIsDeletedFalse(warrantId);
            log.info("Evidence existence check for warrant ID {}: {}", warrantId, exists);
            return exists;
        } catch (Exception ex) {
            log.error("Failed to check evidence existence for warrant ID {}: {}", warrantId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}