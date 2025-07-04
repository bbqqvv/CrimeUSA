package com.Evidence_Service.kafka.consumer;

import com.Evidence_Service.dto.event.caller.EvidenceCreatedEvent;
import com.Evidence_Service.dto.event.listener.*;
import com.Evidence_Service.kafka.handler.EvidenceEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvidenceKafkaConsumer {

    private final EvidenceEventHandler handler;

    @KafkaListener(topics = "case.created", groupId = "evidence-service")
    public void handleCaseCreated(CaseCreatedEvent event) {
        log.info("Received case.created: {}", event);
        handler.onCaseCreated(event);
    }

    @KafkaListener(topics = "suspect.deleted", groupId = "evidence-service")
    public void handleSuspectDeleted(SuspectDeletedEvent event) {
        log.info("Received suspect.deleted: {}", event);
        handler.onSuspectDeleted(event);
    }

    @KafkaListener(topics = "evidence.created", groupId = "evidence-service")
    public void handleEvidenceCreated(EvidenceCreatedEvent event) {
        log.info("📥 Received evidence.created: {}", event);
        handler.onEvidenceCreated(event);
    }

    @KafkaListener(topics = "evidence.analysis-recorded", groupId = "evidence-service")
    public void handleAnalysisResult(AnalysisResultEvent event) {
        log.info("📥 Received evidence.analysis-recorded: {}", event);
        handler.onAnalysisResultRecorded(event);
    }

    @KafkaListener(topics = "evidence.suspect-assigned", groupId = "evidence-service")
    public void handleSuspectAssigned(SuspectAssignedEvent event) {
        log.info("📥 Received evidence.suspect-assigned: {}", event);
        handler.onSuspectAssigned(event);
    }

    @KafkaListener(topics = "evidence.case-assigned", groupId = "evidence-service")
    public void handleCaseAssigned(CaseAssignedEvent event) {
        log.info("📥 Received evidence.case-assigned: {}", event);
        handler.onCaseAssigned(event);
    }

}


