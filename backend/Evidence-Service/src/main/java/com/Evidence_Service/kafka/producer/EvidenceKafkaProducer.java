package com.Evidence_Service.kafka.producer;

import com.Evidence_Service.dto.event.caller.EvidenceCreatedEvent;
import com.Evidence_Service.dto.event.listener.AnalysisResultEvent;
import com.Evidence_Service.dto.event.listener.CaseAssignedEvent;
import com.Evidence_Service.dto.event.listener.SuspectAssignedEvent;
import com.Evidence_Service.dto.event.listener.WarrantAssignedEvent;

public interface EvidenceKafkaProducer {
    void sendEvidenceCreated(EvidenceCreatedEvent event);

    void sendAnalysisResultRecorded(AnalysisResultEvent event);

    void sendSuspectAssigned(SuspectAssignedEvent event);
    void sendCaseAssigned(CaseAssignedEvent event);
    void sendEvidenceDeleted(String evidenceId);
    void sendWarrantAssigned(WarrantAssignedEvent event);
}

