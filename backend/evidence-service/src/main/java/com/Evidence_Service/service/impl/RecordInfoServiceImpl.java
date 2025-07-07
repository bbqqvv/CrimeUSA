package com.Evidence_Service.service.impl;

import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.exception.AppException;
import com.Evidence_Service.exception.ErrorCode;
import com.Evidence_Service.mapper.RecordInfoMapper;
import com.Evidence_Service.model.RecordInfo;
import com.Evidence_Service.repository.RecordInfoRepository;
import com.Evidence_Service.service.RecordInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordInfoServiceImpl implements RecordInfoService {

    private static final Logger log = LoggerFactory.getLogger(RecordInfoServiceImpl.class);

    private final RecordInfoRepository recordInfoRepository;
    private final RecordInfoMapper recordInfoMapper;

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public RecordInfoDTO createRecordInfo(RecordInfoDTO dto) {
        try {
            // Convert DTO to entity and generate unique ID
            RecordInfo entity = recordInfoMapper.toEntity(dto);
            entity.setRecordInfoId(UUID.randomUUID().toString());
            return recordInfoMapper.toDTO(recordInfoRepository.save(entity));
        } catch (Exception ex) {
            log.error("Failed to create record info: {}", ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(value = "recordInfoByAll", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    @Override
    public Page<RecordInfoDTO> getAllRecordInfo(Pageable pageable) {
        try {
            return recordInfoRepository.findByIsDeletedFalse(pageable)
                    .map(recordInfoMapper::toDTO);
        } catch (Exception ex) {
            log.error("Failed to retrieve all record info: {}", ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(value = "recordInfo", key = "#recordInfoId")
    @Override
    public RecordInfoDTO getRecordInfoByRecordInfoId(String recordInfoId) {
        try {
            return recordInfoRepository.findByRecordInfoIdAndIsDeletedFalse(recordInfoId)
                    .map(recordInfoMapper::toDTO)
                    .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));
        } catch (AppException ae) {
            log.error("Application exception during record info retrieval: {}", ae.getErrorCode());
            throw ae;
        } catch (Exception ex) {
            log.error("Failed to retrieve record info with ID {}: {}", recordInfoId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(value = "recordInfoByEvidence", key = "#evidenceId + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    @Override
    public Page<RecordInfoDTO> getRecordInfoByEvidenceId(String evidenceId, Pageable pageable) {
        try {
            return recordInfoRepository.findByEvidenceIdAndIsDeletedFalse(evidenceId, pageable)
                    .map(recordInfoMapper::toDTO);
        } catch (Exception ex) {
            log.error("Failed to retrieve record info for evidence ID {}: {}", evidenceId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public RecordInfoDTO updateRecordInfo(String recordInfoId, RecordInfoDTO dto) {
        try {
            // Find existing record
            recordInfoRepository.findByRecordInfoIdAndIsDeletedFalse(recordInfoId)
                    .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));

            RecordInfo entity;
            // Update entity with new data
            entity = recordInfoMapper.toEntity(dto);
            return recordInfoMapper.toDTO(recordInfoRepository.save(entity));
        } catch (AppException ae) {
            log.error("Application exception during record info update: {}", ae.getErrorCode());
            throw ae;
        } catch (Exception ex) {
            log.error("Failed to update record info with ID {}: {}", recordInfoId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public void deleteRecordInfoByRecordInfoId(String recordInfoId) {
        try {
            // Check if record exists
            if (!recordInfoRepository.existsByRecordInfoIdAndIsDeletedFalse(recordInfoId)) {
                throw new AppException(ErrorCode.RECORD_INFO_NOT_FOUND);
            }
            recordInfoRepository.deleteById(recordInfoId);
        } catch (AppException ae) {
            log.error("Application exception during record info deletion: {}", ae.getErrorCode());
            throw ae;
        } catch (Exception ex) {
            log.error("Failed to delete record info with ID {}: {}", recordInfoId, ex.getMessage(), ex);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}