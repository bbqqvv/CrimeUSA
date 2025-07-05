package com.Evidence_Service.service;

import com.Evidence_Service.dto.MeasureSurveyDTO;
import com.Evidence_Service.dto.RecordInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MeasureSurveyService {

    MeasureSurveyDTO createMeasureSurvey(MeasureSurveyDTO dto);

    Page<MeasureSurveyDTO> getAllMeasureSurvey(Pageable pageable);

    MeasureSurveyDTO getMeasureSurveyByMeasureSurveyId(String measureSurveyId);

    Page<MeasureSurveyDTO> getMeasureSurveyByEvidenceId(String evidenceId, Pageable pageable);

    MeasureSurveyDTO updateMeasureSurvey(String measureSurveyId, RecordInfoDTO dto);

    void deleteMeasureSurveyByMeasureSurveyId(String measureSurveyId);
}
