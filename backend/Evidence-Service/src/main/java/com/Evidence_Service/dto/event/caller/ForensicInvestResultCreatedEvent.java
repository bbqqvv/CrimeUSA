package com.Evidence_Service.dto.event.caller;

import com.Evidence_Service.dto.ForensicInvestResultDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForensicInvestResultCreatedEvent {
    private String resultId;
    private String evidenceId;
    private String investigationPlanId;
    private String labName;
    private String report;
    private String resultSummary;
    private LocalDateTime receivedAt;

    public static ForensicInvestResultCreatedEvent from(ForensicInvestResultDTO dto) {
        return new ForensicInvestResultCreatedEvent(
                dto.getResultId(),
                dto.getEvidenceId(),
                dto.getInvestigationPlanId(),
                dto.getLabName(),
                dto.getReport(),
                dto.getResultSummary(),
                dto.getReceivedAt()
        );
    }
}
