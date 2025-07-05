package com.Evidence_Service.dto;

import lombok.Data;

@Data
public class WarrantDTO {
    private String warrantId;
    private String warrantName;
    private String attachedFile;
    private String timePublish;
    private String officerUsername;
}
