package com.Evidence_Service.controller;

import com.Evidence_Service.dto.*;
import com.Evidence_Service.dto.event.caller.*;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.EvidenceService;
import com.Evidence_Service.service.impl.EvidenceServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences")
@RequiredArgsConstructor
@Tag(name = "Evidence", description = "Management of evidence and related investigation results")
public class EvidenceController {

    private final EvidenceService evidenceService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_EVIDENCE')")
    public ApiResponse<EvidenceDTO> create(@RequestBody EvidenceDTO dto) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Created evidence")
                .data(evidenceService.createEvidence(dto))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<EvidenceDTO> getByEvidenceId(@PathVariable String id) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Evidence found")
                .data(evidenceService.getByEvidenceId(id))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<List<EvidenceDTO>> getListEvidence(
            @RequestParam(required = false) String caseId,
            @RequestParam(required = false) String suspectId
    ) {
        return ApiResponse.<List<EvidenceDTO>>builder()
                .code(200)
                .message("List evidence")
                .data(evidenceService.getByCaseOrSuspect(caseId, suspectId))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_EVIDENCE')")
    public ApiResponse<EvidenceDTO> updateEvidence(@PathVariable String id, @RequestBody EvidenceDTO dto) {
        dto.setEvidenceId(id);
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Updated evidence")
                .data(evidenceService.updateEvidence(dto))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EVIDENCE')")
    public ApiResponse<Void> deleteByEvidenceId(@PathVariable String id) {
        evidenceService.deleteByEvidenceId(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Deleted evidence")
                .build();
    }
}

