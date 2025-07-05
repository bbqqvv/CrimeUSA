package com.Evidence_Service.dto;

import lombok.Data;

@Data
public class CaseDTO {
    private String caseId;
    private String caseNumber;
    private String status;
    private String summary;
    private String severity;
}
