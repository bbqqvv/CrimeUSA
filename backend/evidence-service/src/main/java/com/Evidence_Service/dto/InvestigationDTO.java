package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestigationDTO {

    @NotNull
    @NotBlank
    private String resultId;

    @NotBlank
    private String type;

    @NotBlank
    private String uploadFile;

    @NotBlank
    private String content;
}
