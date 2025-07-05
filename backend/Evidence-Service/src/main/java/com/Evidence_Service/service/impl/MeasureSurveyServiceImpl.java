package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.MeasureSurveyDTO;
import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.mapper.MeasureSurveyMapper;
import com.Evidence_Service.mapper.RecordInfoMapper;
import com.Evidence_Service.model.RecordInfo;
import com.Evidence_Service.repository.MeasureSurveyRepository;
import com.Evidence_Service.repository.RecordInfoRepository;
import com.Evidence_Service.service.MeasureSurveyService;
import com.Evidence_Service.service.RecordInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeasureSurveyServiceImpl implements MeasureSurveyService {

    private final MeasureSurveyRepository measureSurveyRepository;
    private final MeasureSurveyMapper measureSurveyMapper;


    @CacheEvict(value = {"measureSurvey", "measureSurveyByEvidence"}, allEntries = true)
    @Override
    public MeasureSurveyDTO createMeasureSurvey(MeasureSurveyDTO dto) {
        return null;
    }

    @Cacheable(value = "measureSurvey", key = "#measureSurvey")
    @Override
    public Page<MeasureSurveyDTO> getAllMeasureSurvey(Pageable pageable) {
        return null;
    }

    @Cacheable(value = "measureSurvey", key = "#measureSurveyId")
    @Override
    public MeasureSurveyDTO getMeasureSurveyByMeasureSurveyId(String measureSurveyId) {
        return null;
    }

    @Cacheable(value = "measureSurveyByEvidence", key = "#measureSurveyByEvidenceId")
    @Override
    public Page<MeasureSurveyDTO> getMeasureSurveyByEvidenceId(String evidenceId, Pageable pageable) {
        return null;
    }

    @CacheEvict(value = {"measureSurvey", "measureSurveyByEvidence"}, allEntries = true)
    @Override
    public MeasureSurveyDTO updateMeasureSurvey(String measureSurveyId, RecordInfoDTO dto) {
        return null;
    }

    @CacheEvict(value = {"measureSurvey", "measureSurveyByEvidence"}, allEntries = true)
    @Override
    public void deleteMeasureSurveyByMeasureSurveyId(String measureSurveyId) {

    }
}
