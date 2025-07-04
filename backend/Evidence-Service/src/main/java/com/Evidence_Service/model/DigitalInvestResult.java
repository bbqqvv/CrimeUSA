package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseInvestResult;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "digital_invest_result")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalInvestResult extends BaseInvestResult {
    private String deviceType;
    private String analystTool;
    private String result;
}
