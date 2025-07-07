package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseInvestResult;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "forensic_invest_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ForensicInvestResult extends BaseInvestResult {
    private String labName;
    private String report;
    private String resultSummary;
}