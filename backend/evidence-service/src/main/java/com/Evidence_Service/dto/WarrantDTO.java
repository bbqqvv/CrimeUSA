package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarrantDTO {
    @NotNull
    @NotBlank
    private String warrantId;
    @NotBlank
    private String warrantName;
    @NotBlank
    private String attachedFile;
    @NotNull
    private LocalDateTime timePublish;
    @NotBlank
    private String officerUsername;
}
