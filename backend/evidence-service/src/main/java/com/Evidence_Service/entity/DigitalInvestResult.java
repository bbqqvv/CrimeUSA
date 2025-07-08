package com.Evidence_Service.entity;

import com.Evidence_Service.entity.base.BaseInvestResult;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
