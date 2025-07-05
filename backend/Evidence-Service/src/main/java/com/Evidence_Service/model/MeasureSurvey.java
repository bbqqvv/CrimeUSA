package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MeasureSurvey extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String measureSurveyId;

    private String typeName;
    private String source;
    private String resultId;
}
