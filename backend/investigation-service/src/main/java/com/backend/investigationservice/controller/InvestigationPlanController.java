package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.request.InvestigationPlanCreationRequest;
import com.backend.investigationservice.dto.response.InvestigationPlanResponse;
import com.backend.investigationservice.service.InvestigationPlanService;
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

    @GetMapping
    public ResponseEntity<List<InvestigationPlanResponse>> getAllPlans() {
        return ResponseEntity.ok(investigationPlanService.findAll());
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<InvestigationPlanResponse>> getPlansByCaseId(@PathVariable UUID caseId) {
        return ResponseEntity.ok(investigationPlanService.getByCaseId(caseId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InvestigationPlanResponse>> searchPlans(
            @RequestParam(required = false) String keyword,
            Pageable pageable
    ) {
        return ResponseEntity.ok(investigationPlanService.findAll(keyword, pageable));
    }

    @PostMapping
    public ResponseEntity<InvestigationPlanResponse> createPlan(@RequestBody InvestigationPlanCreationRequest request) {
        InvestigationPlanResponse response = investigationPlanService.createPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<InvestigationPlanResponse> updatePlan(
            @PathVariable UUID planId,
            @RequestBody InvestigationPlanCreationRequest request
    ) {
        InvestigationPlanResponse response = investigationPlanService.updatePlan(planId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<InvestigationPlanResponse> deletePlan(
            @PathVariable UUID planId,
            @RequestBody InvestigationPlanCreationRequest request
    ) {
        InvestigationPlanResponse response = investigationPlanService.deletePlan(planId, request);
        return ResponseEntity.ok(response);
    }
} 