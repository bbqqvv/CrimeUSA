package com.Evidence_Service.controller;
import com.Evidence_Service.dto.AssignCaseDTO;
import com.Evidence_Service.dto.AssignSuspectDTO;
import com.Evidence_Service.dto.AssignWarrantDTO;
import com.Evidence_Service.dto.EvidenceDTO;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.EvidenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences/{id}")
@Tag(name = "Evidence Assignment", description = "Seizing evidence with the suspect, the case, warrant.")
@RequiredArgsConstructor
public class EvidenceAssignmentController {

    private final EvidenceService evidenceService;

    @PutMapping("/assign-suspect")
    @PreAuthorize("hasAuthority('ASSIGN_SUSPECT')")
    public ApiResponse<EvidenceDTO> assignSuspect(@PathVariable String id, @RequestBody AssignSuspectDTO dto) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Assigned suspect")
                .data(evidenceService.assignSuspect(id, dto))
                .build();
    }

    @PutMapping("/assign-case")
    @PreAuthorize("hasAuthority('ASSIGN_CASE')")
    public ApiResponse<EvidenceDTO> assignCase(@PathVariable String id, @RequestBody AssignCaseDTO dto) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Assigned case")
                .data(evidenceService.assignCase(id, dto))
                .build();
    }

    @PutMapping("/assign-warrant")
    @PreAuthorize("hasAuthority('ASSIGN_WARRANT')")
    public ApiResponse<EvidenceDTO> assignWarrant(@PathVariable String id, @RequestBody AssignWarrantDTO dto) {
        return ApiResponse.<EvidenceDTO>builder()
                .code(200)
                .message("Assigned warrant")
                .data(evidenceService.assignWarrant(id, dto))
                .build();
    }

    @GetMapping("/suspects")
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<List<String>> getSuspectsByEvidence(@PathVariable String id) {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Suspects fetched")
                .data(evidenceService.getSuspectsByEvidence(id))
                .build();
    }

    @GetMapping("/warrants")
    @PreAuthorize("hasAuthority('VIEW_EVIDENCE')")
    public ApiResponse<List<String>> getWarrantsByEvidence(@PathVariable String id) {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Warrants fetched")
                .data(evidenceService.getWarrantsByEvidence(id))
                .build();
    }
}