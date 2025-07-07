package com.Evidence_Service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SuspectDTO {
    private String suspectId;
    private String suspectName;
    private String fullName;
    private String status;
    private String address;
}
