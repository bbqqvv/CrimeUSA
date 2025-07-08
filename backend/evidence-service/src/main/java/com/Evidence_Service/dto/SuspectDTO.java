package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SuspectDTO {
    @NotNull
    @NotBlank
    private String suspectId;
    @NotBlank
    private String suspectName;
    @NotBlank
    private String fullName;
    @NotBlank
    private String status;
    @NotBlank
    private String address;
}
