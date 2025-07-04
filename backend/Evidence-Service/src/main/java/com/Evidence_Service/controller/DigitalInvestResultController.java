package com.Evidence_Service.controller;
import com.Evidence_Service.dto.DigitalInvestResultDTO;
import com.Evidence_Service.dto.event.caller.DigitalInvestResultCreatedEvent;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.DigitalInvestResultService;
import com.Evidence_Service.service.impl.DigitalInvestResultServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences/{id}/digital-invest")
@Tag(name = "Digital Investigation", description = "Digital investigation results management")
@RequiredArgsConstructor
public class DigitalInvestResultController {

    private final DigitalInvestResultService digitalInvestResultService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_DIGITAL_RESULT')")
    @Operation(summary = "Create digital investigation result")
    public ApiResponse<DigitalInvestResultDTO> createDigitalInvestResult(@PathVariable String id, @RequestBody DigitalInvestResultDTO dto) {
        return ApiResponse.<DigitalInvestResultDTO>builder()
                .code(201)
                .message("Digital investigation result created")
                .data(digitalInvestResultService.addDigitalInvestResult(id, dto))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_DIGITAL_RESULT')")
    @Operation(summary = "Get all digital investigation results")
    public ApiResponse<List<DigitalInvestResultDTO>> getAllDigitalInvestResults(@PathVariable String id) {
        return ApiResponse.<List<DigitalInvestResultDTO>>builder()
                .code(200)
                .message("Fetched digital investigation results")
                .data(digitalInvestResultService.getAllDigitalInvestByEvidenceId(id))
                .build();
    }

    @GetMapping("/{resultId}")
    @PreAuthorize("hasAuthority('VIEW_DIGITAL_RESULT')")
    @Operation(summary = "Get digital investigation result by id")
    public ApiResponse<DigitalInvestResultDTO> getDigitalInvestResultById(@PathVariable String id, @PathVariable String resultId) {
        return ApiResponse.<DigitalInvestResultDTO>builder()
                .code(200)
                .message("Fetched digital investigation result")
                .data(digitalInvestResultService.getDigitalInvestById(resultId))
                .build();
    }

    @PutMapping("/{resultId}")
    @PreAuthorize("hasAuthority('EDIT_DIGITAL_RESULT')")
    @Operation(summary = "Update digital investigation result")
    public ApiResponse<DigitalInvestResultDTO> updateDigitalInvestResult(@PathVariable String id, @PathVariable String resultId, @RequestBody DigitalInvestResultDTO dto) {
        return ApiResponse.<DigitalInvestResultDTO>builder()
                .code(200)
                .message("Digital investigation result updated")
                .data(digitalInvestResultService.updateDigitalInvest(id, resultId, dto))
                .build();
    }

    @DeleteMapping("/{resultId}")
    @PreAuthorize("hasAuthority('DELETE_DIGITAL_RESULT')")
    @Operation(summary = "Delete digital investigation result")
    public ApiResponse<Void> deleteDigitalInvestResult(@PathVariable String id, @PathVariable String resultId) {
        digitalInvestResultService.deleteDigitalInvest(resultId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Digital investigation result deleted")
                .build();
    }
}
