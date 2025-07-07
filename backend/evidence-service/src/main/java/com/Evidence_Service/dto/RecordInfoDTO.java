package com.Evidence_Service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordInfoDTO {
    private String recordInfoId;
    private String evidenceId;
    private String typeName;
    private String source;
    private LocalDateTime dateCollected;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
