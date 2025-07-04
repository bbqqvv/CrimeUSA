package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.*;
import com.Evidence_Service.dto.event.caller.*;
import com.Evidence_Service.dto.event.listener.*;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.kafka.EventPublisher;
import com.Evidence_Service.mapper.EvidenceMapper;
import com.Evidence_Service.model.*;
import com.Evidence_Service.repository.*;
import com.Evidence_Service.service.EvidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.Evidence_Service.mapper.EvidenceMapper.toDTO;
import static com.Evidence_Service.mapper.EvidenceMapper.toEntity;

@Service
@RequiredArgsConstructor
public class EvidenceServiceImpl implements EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final WarrantEvidenceRepository warrantEvidenceRepository;
    private final SuspectEvidenceRepository suspectEvidenceRepository;
    private final CaseEvidenceRepository caseEvidenceRepository;
    private final EventPublisher eventPublisher;

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO createEvidence(EvidenceDTO dto) {
        Evidence entity = toEntity(dto);
        Evidence saved = evidenceRepository.save(entity);
        eventPublisher.send("evidence.created", EvidenceMapper.toCreatedEvent(saved));
        return toDTO(saved);
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public void deleteByEvidenceId(String evidenceId) {
        Evidence evidence = getEvidenceOrThrow(evidenceId);
        evidence.setDeleted(true);
        evidence.setStatus(EvidenceStatus.DELETED);
        evidenceRepository.save(evidence);
        eventPublisher.send("evidence.deleted", new EvidenceDeletedEvent());
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignCase(String id, AssignCaseDTO dto) {
        Evidence evidence = getEvidenceOrThrow(id);
        evidence.setCaseId(dto.getCaseId());
        evidence.setStatus(EvidenceStatus.ASSIGNED);
        evidenceRepository.save(evidence);
        eventPublisher.send("case.assigned", new CaseAssignedEvent(id, dto.getCaseId(), LocalDateTime.now()));
        return toDTO(evidence);
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignSuspect(String evidenceId, AssignSuspectDTO dto) {
        Evidence evidence = getEvidenceOrThrow(evidenceId);

        SuspectEvidence se = new SuspectEvidence();
        se.setEvidenceId(evidenceId);
        se.setSuspectId(dto.getSuspectId());
        suspectEvidenceRepository.save(se);

        eventPublisher.send("suspect.assigned", new SuspectAssignedEvent(evidenceId, dto.getSuspectId(), LocalDateTime.now()));
        return toDTO(evidence);
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignWarrant(String evidenceId, AssignWarrantDTO dto) {
        Evidence evidence = getEvidenceOrThrow(evidenceId);

        WarrantEvidence we = new WarrantEvidence();
        we.setEvidenceId(evidenceId);
        we.setWarrantId(dto.getWarrantId());
        warrantEvidenceRepository.save(we);

        eventPublisher.send("warrant.assigned", new WarrantAssignedEvent(evidenceId, dto.getWarrantId(), LocalDateTime.now()));
        return toDTO(evidence);
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO updateEvidence(EvidenceDTO dto) {
        Evidence entity = getEvidenceOrThrow(dto.getEvidenceId());
        entity.setDescription(dto.getDescription());
        Evidence saved = evidenceRepository.save(entity);
        return toDTO(saved);
    }

    @Cacheable(value = "evidence", key = "#id")
    public EvidenceDTO getByEvidenceId(String id) {
        return EvidenceMapper.toDTO(getEvidenceOrThrow(id));
    }

    @Override
    public boolean existsByEvidenceId(String evidenceId) {
        return evidenceRepository.existsByEvidenceId(evidenceId);
    }

    @Cacheable(value = "evidenceByCaseSuspect", key = "#caseId != null && #suspectId != null ? #caseId + '_' + #suspectId : (#caseId != null ? #caseId : #suspectId)")
    @Override
    public List<EvidenceDTO> getByCaseOrSuspect(String caseId, String suspectId) {
        List<Evidence> evidences;

        if (caseId != null && suspectId != null) {
            List<String> evidenceIdsByCase = caseEvidenceRepository
                    .findByCaseIdAndIsDeletedFalse(caseId)
                    .stream()
                    .map(CaseEvidence::getEvidenceId)
                    .toList();

            List<String> evidenceIdsBySuspect = suspectEvidenceRepository
                    .findBySuspectIdAndIsDeletedFalse(suspectId)
                    .stream()
                    .map(SuspectEvidence::getEvidenceId)
                    .toList();

            evidences = evidenceRepository.findAllById(
                    evidenceIdsByCase.stream()
                            .filter(evidenceIdsBySuspect::contains)
                            .toList()
            );
        } else if (caseId != null) {
            List<String> evidenceIds = caseEvidenceRepository
                    .findByCaseIdAndIsDeletedFalse(caseId)
                    .stream()
                    .map(CaseEvidence::getEvidenceId)
                    .toList();
            evidences = evidenceRepository.findAllById(evidenceIds);
        } else if (suspectId != null) {
            List<String> evidenceIds = suspectEvidenceRepository
                    .findBySuspectIdAndIsDeletedFalse(suspectId)
                    .stream()
                    .map(SuspectEvidence::getEvidenceId)
                    .toList();
            evidences = evidenceRepository.findAllById(evidenceIds);
        } else {
            evidences = evidenceRepository.findAll();
        }

        return evidences.stream()
                .filter(e -> !e.isDeleted())
                .map(EvidenceMapper::toDTO)
                .toList();
    }

    private Evidence getEvidenceOrThrow(String id) {
        return evidenceRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EVIDENCE_NOT_FOUND));
    }

    @Override
    public List<String> getSuspectsByEvidence(String evidenceId) {
        return suspectEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(SuspectEvidence::getSuspectId)
                .toList();
    }

    @Override
    public List<String> getWarrantsByEvidence(String evidenceId) {
        return warrantEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(WarrantEvidence::getWarrantId)
                .toList();
    }

    @Override
    public void removeSuspectFromEvidence(String suspectId) {
        List<SuspectEvidence> suspectEvidenceList = suspectEvidenceRepository.findBySuspectIdAndIsDeletedFalse(suspectId);
        suspectEvidenceList.forEach(suspectEvidence -> {
            suspectEvidence.setDeleted(true);
            suspectEvidence.setDetachedAt(LocalDateTime.now());
        });
        suspectEvidenceRepository.saveAll(suspectEvidenceList);
    }

    @Override
    public void saveEvidenceFromEvent(EvidenceCreatedEvent event) {
        Evidence entity = Evidence.builder()
                .evidenceId(event.getEvidenceId())
                .status(EvidenceStatus.APPROVED)
                .currentLocation(event.getLocation())
                .collectorUsername(event.getCollector_username())
                .build();

        evidenceRepository.save(entity);
    }

    @Override
    public void updateAnalysisResult(AnalysisResultEvent event) {
        Evidence evidence = getEvidenceOrThrow(event.getEvidenceId());
        evidence.setMeasureSurveyId(event.getMeasureSurveyId());
        evidenceRepository.save(evidence);
    }

    @Override
    public void assignSuspectToEvidence(String evidenceId, String suspectId, LocalDateTime assignedAt) {
        SuspectEvidence suspectEvidence = new SuspectEvidence();
        suspectEvidence.setEvidenceId(evidenceId);
        suspectEvidence.setSuspectId(suspectId);
        suspectEvidenceRepository.save(suspectEvidence);
    }

    @Override
    public void assignCaseToEvidence(String evidenceId, String caseId, LocalDateTime assignedAt) {
        CaseEvidence caseEvidence = new CaseEvidence();
        caseEvidence.setEvidenceId(evidenceId);
        caseEvidence.setCaseId(caseId);
        caseEvidenceRepository.save(caseEvidence);
    }
}
