package com.Evidence_Service.controller;
import com.Evidence_Service.dto.ForensicInvestResultDTO;
import com.Evidence_Service.dto.event.caller.ForensicInvestResultCreatedEvent;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.ForensicInvestResultService;
import com.Evidence_Service.service.impl.ForensicInvestResultServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences/{id}/forensic-invest")
@Tag(name = "Forensic Investigation", description = "Forensic investigation results")
@RequiredArgsConstructor
public class ForensicInvestResultController {
    private final ForensicInvestResultService forensicInvestResultService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_FORENSIC_RESULT')")
    @Operation(summary = "Create forensic investigation result")
    public ApiResponse<ForensicInvestResultDTO> createForensicInvestResult(@PathVariable String id, @RequestBody ForensicInvestResultDTO dto) {
        return ApiResponse.<ForensicInvestResultDTO>builder().code(201).message("Created").data(forensicInvestResultService.addForensicInvestResult(id, dto)).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_FORENSIC_RESULT')")
    @Operation(summary = "Get all forensic investigation results")
    public ApiResponse<List<ForensicInvestResultDTO>> getAllForensicInvestResults(@PathVariable String id) {
        return ApiResponse.<List<ForensicInvestResultDTO>>builder().code(200).message("Fetched").data(forensicInvestResultService.getAllForensicInvestByEvidenceId(id)).build();
    }

    @GetMapping("/{resultId}")
    @PreAuthorize("hasAuthority('VIEW_FORENSIC_RESULT')")
    @Operation(summary = "Get forensic investigation result by ID")
    public ApiResponse<ForensicInvestResultDTO> getForensicInvestResultById(@PathVariable String id, @PathVariable String resultId) {
        return ApiResponse.<ForensicInvestResultDTO>builder().code(200).message("Fetched").data(forensicInvestResultService.getForensicInvestById(resultId)).build();
    }

    @PutMapping("/{resultId}")
    @PreAuthorize("hasAuthority('EDIT_FORENSIC_RESULT')")
    @Operation(summary = "Update forensic investigation result")
    public ApiResponse<ForensicInvestResultDTO> updateForensicInvestResult(@PathVariable String id, @PathVariable String resultId, @RequestBody ForensicInvestResultDTO dto) {
        return ApiResponse.<ForensicInvestResultDTO>builder().code(200).message("Updated").data(forensicInvestResultService.updateForensicInvest(id, resultId, dto)).build();
    }

    @DeleteMapping("/{resultId}")
    @PreAuthorize("hasAuthority('DELETE_FORENSIC_RESULT')")
    @Operation(summary = "Delete forensic investigation result")
    public ApiResponse<Void> deleteForensicInvestResult(@PathVariable String id, @PathVariable String resultId) {
        forensicInvestResultService.deleteForensicInvest(resultId);
        return ApiResponse.<Void>builder().code(200).message("Deleted").build();
    }
}

