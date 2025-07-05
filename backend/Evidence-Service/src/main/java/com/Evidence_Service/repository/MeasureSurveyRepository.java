package com.Evidence_Service.repository;

import com.Evidence_Service.model.Evidence;
import com.Evidence_Service.model.MeasureSurvey;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureSurveyRepository extends JpaRepository<MeasureSurvey, String> {
    MeasureSurvey findByMeasureSurveyIdAndIsDeletedFalse(String measureSurveyId);
    boolean existsByMeasureSurveyIdAndIsDeletedFalse(String measureSurveyId);
}
