package com.Evidence_Service.service;

import com.Evidence_Service.dto.RecordInfoDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface RecordInfoService {

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    RecordInfoDTO createRecordInfo(RecordInfoDTO dto);

    @Cacheable(value = "recordInfo", key = "#id")
    RecordInfoDTO getRecordInfoByRecordInfoId(String id);

    @Cacheable(value = "recordInfoByEvidence", key = "#evidenceId")
    List<RecordInfoDTO> getRecordInfoByEvidenceId(String evidenceId);

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    RecordInfoDTO updateRecordInfo(String id, RecordInfoDTO dto);

    @CacheEvict(value = {"recordInfo", "recordInfoByEvidence"}, allEntries = true)
    void deleteRecordInfoByRecordInfoId(String id);
}
