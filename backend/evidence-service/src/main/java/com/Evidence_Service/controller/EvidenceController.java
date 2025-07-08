package com.Evidence_Service.controller;

import com.Evidence_Service.dto.*;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.EvidenceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "X-API-KEY")
@RestController
@RequestMapping("/api/v1/evidences")
@RequiredArgsConstructor
@Tag(name = "Evidence", description = "Management of evidence and related investigation results")
public class EvidenceController {

    private final EvidenceService evidenceService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_EVIDENCE')")
    public ApiResponse<EvidenceDTO> create(@Valid  @RequestBody EvidenceDTO dto) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Created evidence")
                .data(evidenceService.createEvidence(dto))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<EvidenceDTO> getByEvidenceId(@Valid @PathVariable String evidenceId) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Evidence found")
                .data(evidenceService.getByEvidenceId(evidenceId))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<Page<EvidenceDTO>> getByCaseOrSuspect(@Valid
            @RequestParam(required = false) String caseId,
            @RequestParam(required = false) String suspectId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EvidenceDTO> result = evidenceService.getAllEvidence(pageable);
        return ApiResponse.<Page<EvidenceDTO>>builder()
                .code(200)
                .message("List evidence by case of suspect")
                .data(result)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_EVIDENCE')")
    public ApiResponse<EvidenceDTO> updateEvidence(@Valid @PathVariable String evidenceId, @RequestBody EvidenceDTO dto) {
        dto.setEvidenceId(evidenceId);
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Updated evidence")
                .data(evidenceService.updateEvidence(dto))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EVIDENCE')")
    public ApiResponse<Void> deleteByEvidenceId(@Valid @PathVariable String evidenceId) {
        evidenceService.deleteByEvidenceId(evidenceId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Deleted evidence")
                .build();
    }
}

