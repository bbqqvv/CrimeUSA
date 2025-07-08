package com.Evidence_Service.controller;


import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.RecordInfoService;
import com.Evidence_Service.service.impl.RecordInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/record-info")
@Tag(name = "Record Info", description = "Management of images/videos/evidence attachments")
@RequiredArgsConstructor
public class RecordInfoController {
    private final RecordInfoService recordInfoService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_RECORD_INFO')")
    @Operation(summary = "Create new record info")
    public ApiResponse<RecordInfoDTO> createRecordInfo(@Valid  @RequestBody RecordInfoDTO dto) {
        return ApiResponse.<RecordInfoDTO>builder()
                .code(201)
                .message("Record info created")
                .data(recordInfoService.createRecordInfo(dto))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_RECORD_INFO')")
    @Operation(summary = "Get record info by ID")
    public ApiResponse<RecordInfoDTO> getRecordInfoById(@Valid @PathVariable String recordInfoId) {
        return ApiResponse.<RecordInfoDTO>builder()
                .code(200)
                .message("Fetched record info")
                .data(recordInfoService.getRecordInfoByRecordInfoId(recordInfoId))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_RECORD_INFO')")
    @Operation(summary = "Get all record info by evidence ID")
    public ApiResponse<Page<RecordInfoDTO>> getAllRecordInfoByEvidenceId(@Valid @RequestParam String evidenceId,
                                                                         @RequestParam(defaultValue = "0", required = false) int page,
                                                                         @RequestParam(defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(size, page);
        Page<RecordInfoDTO> result = recordInfoService.getRecordInfoByEvidenceId(evidenceId, pageable);
        return ApiResponse.<Page<RecordInfoDTO>>builder()
                .code(200)
                .message("Fetched all record info")
                .data(result)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_RECORD_INFO')")
    @Operation(summary = "Update record info")
    public ApiResponse<RecordInfoDTO> updateRecordInfo(@Valid @PathVariable String recordInfoId, @RequestBody RecordInfoDTO dto) {
        return ApiResponse.<RecordInfoDTO>builder()
                .code(200)
                .message("Record info updated")
                .data(recordInfoService.updateRecordInfo(recordInfoId, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_RECORD_INFO')")
    @Operation(summary = "Delete record info")
    public ApiResponse<Void> deleteRecordInfo(@Valid @PathVariable String recordInfoId) {
        recordInfoService.deleteRecordInfoByRecordInfoId(recordInfoId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Record info deleted")
                .build();
    }
}
