package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.mapper.RecordInfoMapper;
import com.Evidence_Service.model.RecordInfo;
import com.Evidence_Service.repository.RecordInfoRepository;
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
public class RecordInfoServiceImpl implements RecordInfoService {

    private final RecordInfoRepository recordInfoRepository;
    private final RecordInfoMapper recordInfoMapper;

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public RecordInfoDTO createRecordInfo(RecordInfoDTO dto) {
        RecordInfo entity = recordInfoMapper.toEntity(dto);
        entity.setRecordInfoId(UUID.randomUUID().toString());
        return recordInfoMapper.toDTO(recordInfoRepository.save(entity));
    }

    @Override
    public Page<RecordInfoDTO> getAllRecordInfo(Pageable pageable) {
        return recordInfoRepository.findByIsDeletedFalse(pageable)
                .map(recordInfoMapper::toDTO);
    }

    @Cacheable(value = "recordInfo", key = "#recordInfoId")
    @Override
    public RecordInfoDTO getRecordInfoByRecordInfoId(String recordInfoId) {
        return recordInfoRepository.findByRecordInfoIdAndIsDeletedFalse(recordInfoId)
                .map(recordInfoMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));
    }

    @Cacheable(value = "recordInfoByEvidence", key = "#evidenceId")
    @Override
    public Page<RecordInfoDTO> getRecordInfoByEvidenceId(String evidenceId, Pageable pageable) {
        return recordInfoRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId, pageable)
                .map(recordInfoMapper::toDTO);
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public RecordInfoDTO updateRecordInfo(String recordInfoId, RecordInfoDTO dto) {
        var entity = recordInfoRepository.findByRecordInfoIdAndIsDeletedFalse(recordInfoId)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));
        entity = recordInfoMapper.toEntity(dto);
        return recordInfoMapper.toDTO(recordInfoRepository.save(entity));
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public void deleteRecordInfoByRecordInfoId(String recordInfoId) {
        if (!recordInfoRepository.existsByRecordInfoIdAndIsDeletedFalse(recordInfoId)) {
            throw new AppException(ErrorCode.RECORD_INFO_NOT_FOUND);
        }
        recordInfoRepository.deleteById(recordInfoId);
    }
}
