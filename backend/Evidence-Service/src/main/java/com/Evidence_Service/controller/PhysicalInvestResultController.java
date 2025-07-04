package com.Evidence_Service.controller;


import com.Evidence_Service.dto.PhysicalInvestResultDTO;
import com.Evidence_Service.dto.event.caller.PhysicalInvestResultCreatedEvent;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.PhysicalInvestResultService;
import com.Evidence_Service.service.impl.PhysicalInvestResultServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences/{id}/physical-invest")
@Tag(name = "Physical Investigation", description = "Physical investigation results")
@RequiredArgsConstructor
public class PhysicalInvestResultController {
    private final PhysicalInvestResultService physicalInvestResultService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_PHYSICAL_RESULT')")
    @Operation(summary = "Create physical investigation result")
    public ApiResponse<PhysicalInvestResultDTO> createPhysicalInvestResult(@PathVariable String id, @RequestBody PhysicalInvestResultDTO dto) {
        return ApiResponse.<PhysicalInvestResultDTO>builder().code(201).message("Created").data(physicalInvestResultService.addPhysicalInvestResult(id, dto)).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_PHYSICAL_RESULT')")
    @Operation(summary = "Get all physical investigation results")
    public ApiResponse<List<PhysicalInvestResultDTO>> getAllPhysicalInvestResults(@PathVariable String id) {
        return ApiResponse.<List<PhysicalInvestResultDTO>>builder().code(200).message("Fetched").data(physicalInvestResultService.getAllPhysicalInvestByEvidenceId(id)).build();
    }

    @GetMapping("/{resultId}")
    @PreAuthorize("hasAuthority('VIEW_PHYSICAL_RESULT')")
    @Operation(summary = "Get physical investigation result by ID")
    public ApiResponse<PhysicalInvestResultDTO> getPhysicalInvestResultById(@PathVariable String id, @PathVariable String resultId) {
        return ApiResponse.<PhysicalInvestResultDTO>builder().code(200).message("Fetched").data(physicalInvestResultService.getPhysicalInvestById(resultId)).build();
    }

    @PutMapping("/{resultId}")
    @PreAuthorize("hasAuthority('EDIT_PHYSICAL_RESULT')")
    @Operation(summary = "Update physical investigation result")
    public ApiResponse<PhysicalInvestResultDTO> updatePhysicalInvestResult(@PathVariable String id, @PathVariable String resultId, @RequestBody PhysicalInvestResultDTO dto) {
        return ApiResponse.<PhysicalInvestResultDTO>builder().code(200).message("Updated").data(physicalInvestResultService.updatePhysicalInvest(id, resultId, dto)).build();
    }

    @DeleteMapping("/{resultId}")
    @PreAuthorize("hasAuthority('DELETE_PHYSICAL_RESULT')")
    @Operation(summary = "Delete physical investigation result")
    public ApiResponse<Void> deletePhysicalInvestResult(@PathVariable String id, @PathVariable String resultId) {
        physicalInvestResultService.deletePhysicalInvest(resultId);
        return ApiResponse.<Void>builder().code(200).message("Deleted").build();
    }
}
