package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseInvestResult;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "physical_invest_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PhysicalInvestResult extends BaseInvestResult {
    private PhysicalInvestStatus status;
    private String notes;
    private String imageUrl;
}