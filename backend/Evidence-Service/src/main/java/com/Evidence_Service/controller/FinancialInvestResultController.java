package com.Evidence_Service.controller;

import com.Evidence_Service.dto.FinancialInvestResultDTO;
import com.Evidence_Service.dto.event.caller.FinancialInvestResultCreatedEvent;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.FinancialInvestResultService;
import com.Evidence_Service.service.impl.FinancialInvestResultServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences/{id}/financial-invest")
@Tag(name = "Financial Investigation", description = "Financial investigation results")
@RequiredArgsConstructor
public class FinancialInvestResultController {
    private final FinancialInvestResultService financialInvestResultService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_FINANCIAL_RESULT')")
    @Operation(summary = "Create financial investigation result")
    public ApiResponse<FinancialInvestResultDTO> createFinancialInvestResult(@PathVariable String id, @RequestBody FinancialInvestResultDTO dto) {
        return ApiResponse.<FinancialInvestResultDTO>builder().code(201).message("Created").data(financialInvestResultService.addFinancialInvestResult(id, dto)).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_FINANCIAL_RESULT')")
    @Operation(summary = "Get all financial investigation results")
    public ApiResponse<List<FinancialInvestResultDTO>> getAllFinancialInvestResults(@PathVariable String id) {
        return ApiResponse.<List<FinancialInvestResultDTO>>builder().code(200).message("Fetched").data(financialInvestResultService.getAllFinancialInvestByEvidenceId(id)).build();
    }

    @GetMapping("/{resultId}")
    @PreAuthorize("hasAuthority('VIEW_FINANCIAL_RESULT')")
    @Operation(summary = "Get financial investigation result by ID")
    public ApiResponse<FinancialInvestResultDTO> getFinancialInvestResultById(@PathVariable String id, @PathVariable String resultId) {
        return ApiResponse.<FinancialInvestResultDTO>builder().code(200).message("Fetched").data(financialInvestResultService.getFinancialInvestById(resultId)).build();
    }

    @PutMapping("/{resultId}")
    @PreAuthorize("hasAuthority('EDIT_FINANCIAL_RESULT')")
    @Operation(summary = "Update financial investigation result")
    public ApiResponse<FinancialInvestResultDTO> updateFinancialInvestResult(@PathVariable String id, @PathVariable String resultId, @RequestBody FinancialInvestResultDTO dto) {
        return ApiResponse.<FinancialInvestResultDTO>builder().code(200).message("Updated").data(financialInvestResultService.updateFinancialInvest(id, resultId, dto)).build();
    }

    @DeleteMapping("/{resultId}")
    @PreAuthorize("hasAuthority('DELETE_FINANCIAL_RESULT')")
    @Operation(summary = "Delete financial investigation result")
    public ApiResponse<Void> deleteFinancialInvestResult(@PathVariable String id, @PathVariable String resultId) {
        financialInvestResultService.deleteFinancialInvest(resultId);
        return ApiResponse.<Void>builder().code(200).message("Deleted").build();
    }
}

