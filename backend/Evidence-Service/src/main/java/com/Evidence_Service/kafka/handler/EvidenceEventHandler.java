package com.Evidence_Service.kafka.handler;

import com.Evidence_Service.dto.event.caller.EvidenceCreatedEvent;
import com.Evidence_Service.dto.event.listener.*;
import com.Evidence_Service.service.EvidenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvidenceEventHandler {

    private final EvidenceService evidenceService;

    public void onCaseCreated(CaseCreatedEvent event) {
        log.info("Handling CaseCreatedEvent: {}", event);
    }

    public void onSuspectDeleted(SuspectDeletedEvent event) {
        log.info("Handling SuspectDeletedEvent: {}", event);
        evidenceService.removeSuspectFromEvidence(event.getSuspectId());
    }

    public void onEvidenceCreated(EvidenceCreatedEvent event) {
        log.info("Handling EvidenceCreatedEvent: {}", event);
        evidenceService.saveEvidenceFromEvent(event);
    }

    public void onAnalysisResultRecorded(AnalysisResultEvent event) {
        log.info("Handling AnalysisResultEvent: {}", event);
        evidenceService.updateAnalysisResult(event);
    }

    public void onSuspectAssigned(SuspectAssignedEvent event) {
        log.info("Handling SuspectAssignedEvent: {}", event);
        evidenceService.assignSuspectToEvidence(event.getEvidenceId(), event.getSuspectId(), event.getAssignedAt());
    }

    public void onCaseAssigned(CaseAssignedEvent event) {
        log.info("Handling CaseAssignedEvent: {}", event);
        evidenceService.assignCaseToEvidence(event.getEvidenceId(), event.getCaseId(), event.getAssignedAt());
    }
}
