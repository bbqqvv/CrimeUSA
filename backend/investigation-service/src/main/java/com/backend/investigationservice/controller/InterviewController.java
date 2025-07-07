package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.request.InterviewCreationRequest;
import com.backend.investigationservice.dto.request.InterviewUpdateRequest;
import com.backend.investigationservice.dto.response.InterviewResponse;
import com.backend.investigationservice.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/interviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponse> createInterview(@RequestBody InterviewCreationRequest request) {
        InterviewResponse response = interviewService.createInterview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewResponse> getInterviewById(@PathVariable UUID interviewId) {
        InterviewResponse response = interviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getAllInterviews() {
        List<InterviewResponse> responses = interviewService.getAllInterviews();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/investigation-plan/{investigationPlanId}")
    public ResponseEntity<List<InterviewResponse>> getInterviewsByInvestigationPlan(@PathVariable UUID investigationPlanId) {
        List<InterviewResponse> responses = interviewService.getInterviewsByInvestigationPlan(investigationPlanId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{interviewId}")
    public ResponseEntity<InterviewResponse> updateInterview(@PathVariable UUID interviewId, @RequestBody InterviewUpdateRequest request) {
        InterviewResponse response = interviewService.updateInterview(interviewId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<Void> deleteInterview(@PathVariable UUID interviewId) {
        interviewService.deleteInterview(interviewId);
        return ResponseEntity.noContent().build();
    }
} 