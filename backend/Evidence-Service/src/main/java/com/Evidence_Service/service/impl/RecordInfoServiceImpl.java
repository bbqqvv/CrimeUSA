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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Cacheable(value = "recordInfo", key = "#id")
    @Override
    public RecordInfoDTO getRecordInfoByRecordInfoId(String id) {
        return recordInfoRepository.findById(id)
                .map(recordInfoMapper::toDTO)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));
    }

    @Cacheable(value = "recordInfoByEvidence", key = "#evidenceId")
    @Override
    public List<RecordInfoDTO> getRecordInfoByEvidenceId(String evidenceId) {
        return recordInfoRepository.findByEvidenceId(evidenceId)
                .stream()
                .map(recordInfoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public RecordInfoDTO updateRecordInfo(String id, RecordInfoDTO dto) {
        var entity = recordInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_INFO_NOT_FOUND));
        entity = recordInfoMapper.toEntity(dto);
        return recordInfoMapper.toDTO(recordInfoRepository.save(entity));
    }

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    @Override
    public void deleteRecordInfoByRecordInfoId(String id) {
        if (!recordInfoRepository.existsById(id)) {
            throw new AppException(ErrorCode.RECORD_INFO_NOT_FOUND);
        }
        recordInfoRepository.deleteById(id);
    }
}
