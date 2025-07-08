package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CaseDTO {
    @NotBlank
    @NotNull
    private String caseId;

    @NotBlank
    private String caseNumber;

    @NotBlank
    private String status;

    @NotBlank
    private String summary;

    @NotBlank
    private String severity;
}
