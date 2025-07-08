package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.request.InvestigationPlanCreationRequest;
import com.backend.investigationservice.dto.response.InvestigationPlanResponse;
import com.backend.investigationservice.service.InvestigationPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/investigation-plans")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvestigationPlanController {

    private final InvestigationPlanService investigationPlanService;

    // GET ALL (non-deleted)
    @GetMapping
    public ResponseEntity<List<InvestigationPlanResponse>> getAllPlans() {
        List<InvestigationPlanResponse> plans = investigationPlanService.findAll();
        return ResponseEntity.ok(plans);
    }

    // GET paginated with keyword search
    @GetMapping("/search")
    public ResponseEntity<Page<InvestigationPlanResponse>> searchPlans(
            @RequestParam(required = false) String keyword,
            Pageable pageable
    ) {
        Page<InvestigationPlanResponse> plans = investigationPlanService.findAll(keyword, pageable);
        return ResponseEntity.ok(plans);
    }

    // GET by caseId
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<InvestigationPlanResponse>> getPlansByCaseId(@PathVariable UUID caseId) {
        List<InvestigationPlanResponse> plans = investigationPlanService.getByCaseId(caseId);
        return ResponseEntity.ok(plans);
    }

    // GET paginated by caseId
    @GetMapping("/by-case")
    public ResponseEntity<Page<InvestigationPlanResponse>> getByCaseId(
            @RequestParam UUID caseId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(investigationPlanService.getByCaseId(caseId, pageable));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<InvestigationPlanResponse> createPlan(
            @RequestBody @Valid InvestigationPlanCreationRequest request
    ) {
        InvestigationPlanResponse created = investigationPlanService.createPlan(request);
        return ResponseEntity.ok(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<InvestigationPlanResponse> updatePlan(
            @PathVariable UUID id,
            @RequestBody @Valid InvestigationPlanCreationRequest request
    ) {
        InvestigationPlanResponse updated = investigationPlanService.updatePlan(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<InvestigationPlanResponse> deletePlan(@PathVariable UUID id) {
        InvestigationPlanResponse deleted = investigationPlanService.deletePlan(id);
        return ResponseEntity.ok(deleted);
    }
} 