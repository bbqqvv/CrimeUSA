package com.Evidence_Service.kafka.handler;

import com.Evidence_Service.dto.EvidenceDTO;
import com.Evidence_Service.event.listener.*;
import com.Evidence_Service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvidenceServiceEventHandler {

    private final EvidenceService evidenceService;
    private final PhysicalInvestResultService physicalInvestResultService;
    private final ForensicInvestResultService forensicInvestResultService;
    private final FinancialInvestResultService financialInvestResultService;
    private final DigitalInvestResultService digitalInvestResultService;


    public void onReportCreated(ReportCreatedEvent event) {
        log.info("Handling ReportCreatedEvent: {}", event);
        if (!evidenceService.existsByReportId(event.getReportId())){
            evidenceService.createEvidence(new EvidenceDTO());
        }
    }

    public void onReportDeleted(ReportDeletedEvent event) {
        log.info("Handling ReportDeletedEvent: {}", event);
        //remove
        evidenceService.deleteByReportId(event.getReportId());
    }

    public void onCaseCreated(CaseCreatedEvent event) {
        log.info("Handling CaseCreatedEvent: {}", event);
        if (!evidenceService.existsByCaseId(event.getCaseId())){
            evidenceService.createEvidence(new EvidenceDTO());
        }
    }

    public void onCaseDeleted(CaseDeletedEvent event) {
        log.info("Handling CaseDeletedEvent: {}", event);
        evidenceService.deleteByCaseId(event.getCaseId());
    }

    public void onSuspectDeleted(SuspectDeletedEvent event) {
        log.info("Handling SuspectDeletedEvent: {}", event);
        evidenceService.deleteBySuspectId(event.getSuspectId());
    }

    public void onWarrantCreated(WarrantAssignedEvent event) {
        log.info("Handling WarrantAssignedEvent: {}", event);
        if (!evidenceService.existsByWarrantId(event.getWarrantResultId())){
            evidenceService.createEvidence(new EvidenceDTO());
        }
    }

    public void onWarrantDeleted(WarrantDeletedEvent event) {
        log.info("Handling WarrantDeletedEvent: {}", event);
        evidenceService.deleteByWarrantId(event.getWarrantResultId());
    }

    public void onAnalysisResultRecorded(AnalysisResultEvent event) {
        log.info("Handling AnalysisResultEvent: {}", event);
        evidenceService.updateAnalysisResult(event);
    }

    public void onSuspectAssigned(SuspectAssignedEvent event) {
        log.info("Handling SuspectAssignedEvent: {}", event);
        evidenceService.assignSuspectToEvidence(event.getEvidenceId(), event.getSuspectId());
    }

    public void onCaseAssigned(CaseAssignedEvent event) {
        log.info("Handling CaseAssignedEvent: {}", event);
        evidenceService.assignCaseToEvidence(event.getEvidenceId(), event.getCaseId());
    }

    public void onInvestigationCreated(ResultInvestAssignedEvent event) {
        log.info("Handling ResultInvestAssignedEvent: {}", event);
        switch (event.getType()) {
            case "physical": physicalInvestResultService.assignPhysicalInvestResult(event.getInvestigationPlanId(), event.getUploadFile(), event.getContent());
            case "digital" : digitalInvestResultService.assignDigitalInvestResult(event.getInvestigationPlanId(), event.getUploadFile(), event.getContent());
            case "financial": financialInvestResultService.assignFinancialInvestResult(event.getInvestigationPlanId(), event.getUploadFile(), event.getContent());
            case "forensic" : forensicInvestResultService.assignForensicInvestResult(event.getInvestigationPlanId(), event.getUploadFile(), event.getContent());
            default:
                log.info("Handling CreateInvestResult False");

        }
    }

    public void onInvestigationDeleted(DeleteInvestResultEvent event) {
        switch (event.getType()) {
            case "physical": physicalInvestResultService.deletePhysicalInvestByResultId( event.getResultId());
            case "digital" : digitalInvestResultService.deleteDigitalInvestByResultId(event.getResultId());
            case "financial": financialInvestResultService.deleteFinancialInvestByResultId(event.getResultId());
            case "forensic" : forensicInvestResultService.deleteForensicInvestByResultId(event.getResultId());
            default:
                log.info("Handling UpdateInvestResult False");
        }
    }
}
