package com.Evidence_Service.service;

import com.Evidence_Service.dto.AssignCaseDTO;
import com.Evidence_Service.dto.AssignSuspectDTO;
import com.Evidence_Service.dto.AssignWarrantDTO;
import com.Evidence_Service.dto.EvidenceDTO;
import com.Evidence_Service.dto.event.listener.AnalysisResultEvent;
import com.Evidence_Service.dto.event.caller.EvidenceCreatedEvent;
import com.Evidence_Service.model.Evidence;

import java.time.LocalDateTime;
import java.util.List;

public interface EvidenceService {

    EvidenceDTO createEvidence(EvidenceDTO dto);

    EvidenceDTO getByEvidenceId(String evidenceId);
    EvidenceDTO updateEvidence(EvidenceDTO dto);

    void deleteByEvidenceId(String id);

    EvidenceDTO assignCase(String id, AssignCaseDTO dto);
    EvidenceDTO assignSuspect(String id, AssignSuspectDTO dto);
    EvidenceDTO assignWarrant(String id, AssignWarrantDTO dto);
    List<String> getSuspectsByEvidence(String evidenceId);
    List<String> getWarrantsByEvidence(String evidenceId);

    void removeSuspectFromEvidence(String suspectId);
    void saveEvidenceFromEvent(EvidenceCreatedEvent event);
    void updateAnalysisResult(AnalysisResultEvent event);
    void assignSuspectToEvidence(String evidenceId, String suspectId, LocalDateTime assignedAt);
    void assignCaseToEvidence(String evidenceId, String caseId, LocalDateTime assignedAt);

    List<EvidenceDTO> getByCaseOrSuspect(String caseId, String suspectId);
}