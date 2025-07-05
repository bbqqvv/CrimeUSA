package com.Evidence_Service.service.impl;

import com.Evidence_Service.client.ReportClient;
import com.Evidence_Service.client.CaseClient;
import com.Evidence_Service.client.SuspectClient;
import com.Evidence_Service.client.WarrantClient;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ReportEvidenceRepository reportEvidenceRepository;
    private final ReportClient reportClient;
    private final CaseClient caseClient;
    private final WarrantClient warrantClient;
    private final SuspectClient suspectClient;
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
    public EvidenceDTO assignCase(String evidenceId, CaseDTO dto) {
        Evidence evidence = getEvidenceOrThrow(evidenceId);
        evidence.setCaseId(dto.getCaseId());
        evidence.setStatus(EvidenceStatus.ASSIGNED);
        evidenceRepository.save(evidence);
        eventPublisher.send("case.assigned", new CaseAssignedEvent(evidenceId, dto.getCaseId(), LocalDateTime.now()));
        return toDTO(evidence);
    }

    @CacheEvict(value = {"evidence", "evidenceByCaseSuspect"}, allEntries = true)
    @Override
    public EvidenceDTO assignSuspect(String evidenceId, SuspectDTO dto) {
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
    public EvidenceDTO assignWarrant(String evidenceId, WarrantDTO dto) {
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
    public EvidenceDTO getByEvidenceId(String evidenceId) {
        return EvidenceMapper.toDTO(getEvidenceOrThrow(evidenceId));
    }

    @Override
    public boolean existsByEvidenceId(String evidenceId) {
        return evidenceRepository.existsByEvidenceIdAndIsDeletedFalse(evidenceId);
    }

    @Override
    public void deleteByReportId(String reportId) {
        List<ReportEvidence> reportEvidences = reportEvidenceRepository.findByReportIdAndIsDeletedFalse(reportId);
        reportEvidences.forEach(reportEvidence -> reportEvidence.setDeleted(true));
    }

    @Override
    public void deleteByCaseId(String caseId) {
        List<CaseEvidence> caseEvidences = caseEvidenceRepository.findByCaseIdAndIsDeletedFalse(caseId);
        caseEvidences.forEach(caseEvidence -> caseEvidence.setDeleted(true));
    }

    @Override
    public void deleteByWarrantId(String warrantId) {
        List<WarrantEvidence> warrantEvidences = warrantEvidenceRepository.findByWarrantIdAndIsDeletedFalse(warrantId);
        warrantEvidences.forEach(warrantEvidence -> warrantEvidence.setDeleted(true));
    }

    @Override
    public void deleteBySuspectId(String suspectId) {
        List<SuspectEvidence> suspectEvidences = suspectEvidenceRepository.findBySuspectIdAndIsDeletedFalse(suspectId);
        suspectEvidences.forEach(suspectEvidence -> suspectEvidence.setDeleted(true));
    }

    @Override
    @Cacheable(value = "evidences", key = "#evidenceIds")
    public Page<EvidenceDTO> getAllEvidence(Pageable pageable) {
        return evidenceRepository.findAllNotDeleted(pageable)
                .map(EvidenceMapper::toDTO);
    }

    private Evidence getEvidenceOrThrow(String evidenceId) {
        return evidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .orElseThrow(() -> new AppException(ErrorCode.EVIDENCE_NOT_FOUND));
    }

    @Override
    public List<SuspectDTO> getSuspectsByEvidence(String evidenceId) {
        List<String> suspectIds = suspectEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(SuspectEvidence::getSuspectId)
                .toList();
        return suspectClient.getSuspectByIds(suspectIds);
    }

    @Override
    public List<WarrantDTO> getWarrantsByEvidence(String evidenceId) {
        List<String> warrantIds = warrantEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(WarrantEvidence::getWarrantId)
                .toList();
        return warrantClient.getWarrantByIds(warrantIds);
    }
    @Override
    public List<CaseDTO> getCasesByEvidence(String evidenceId) {
        List<String> caseIds = caseEvidenceRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(CaseEvidence::getCaseId)
                .toList();
        return caseClient.getCasesByIds(caseIds);
    }

    @Override
    public List<ReportDTO> getReportsByEvidence(String evidenceId) {
        List<String> reportIds = reportEvidenceRepository.findByReportIdAndIsDeletedFalse(evidenceId)
                .stream()
                .map(ReportEvidence::getReportId)
                .toList();
        return reportClient.getReportsByIds(reportIds);
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
    public void assignSuspectToEvidence(String evidenceId, String suspectId) {
        SuspectEvidence suspectEvidence = new SuspectEvidence();
        suspectEvidence.setEvidenceId(evidenceId);
        suspectEvidence.setSuspectId(suspectId);
        suspectEvidenceRepository.save(suspectEvidence);
    }

    @Override
    public void assignCaseToEvidence(String evidenceId, String caseId) {
        CaseEvidence caseEvidence = new CaseEvidence();
        caseEvidence.setEvidenceId(evidenceId);
        caseEvidence.setCaseId(caseId);
        caseEvidenceRepository.save(caseEvidence);
    }

    @Override
    public void assignWarrantToEvidence(String evidenceId, String warrantId) {
        WarrantEvidence warrantEvidence = new WarrantEvidence();
        warrantEvidence.setEvidenceId(evidenceId);
        warrantEvidence.setWarrantId(warrantId);
        warrantEvidenceRepository.save(warrantEvidence);
    }

    @Override
    public void assignReportToEvidence(String evidenceId, String reportId) {
        ReportEvidence reportEvidence = new ReportEvidence();
        reportEvidence.setEvidenceId(evidenceId);
        reportEvidence.setReportId(reportId);
        reportEvidenceRepository.save(reportEvidence);
    }

    @Override
    public boolean existsByReportId(String reportId) {
        return reportEvidenceRepository.existsByReportIdAndIsDeletedFalse(reportId);
    }

    @Override
    public boolean existsByCaseId(String caseId) {
        return caseEvidenceRepository.existsByCaseIdAndIsDeletedFalse(caseId);
    }

    @Override
    public boolean existsBySuspectId(String suspectId) {
        return suspectEvidenceRepository.existsBySuspectIdAndIsDeletedFalse(suspectId);

    }

    @Override
    public boolean existsByWarrantId(String warrantId) {
        return warrantEvidenceRepository.existsByWarrantIdAndIsDeletedFalse(warrantId);
    }
}
