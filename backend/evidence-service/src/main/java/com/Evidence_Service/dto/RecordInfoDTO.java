package com.Evidence_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordInfoDTO {
    @NotNull
    @NotBlank
    private String recordInfoId;

    @NotNull
    @NotBlank
    private String evidenceId;

    @NotBlank
    private String typeName;

    @NotBlank
    private String source;

    @NotNull
    private LocalDateTime dateCollected;

    @NotBlank
    private String summary;

    @NotNull
    @NotBlank
    private LocalDateTime createdAt;

    @NotNull
    @NotBlank
    private LocalDateTime updatedAt;

    private boolean isDeleted;
}
