package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.request.InterviewCreationRequest;
import com.backend.investigationservice.dto.request.InterviewUpdateRequest;
import com.backend.investigationservice.dto.response.InterviewResponse;
import com.backend.investigationservice.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    /**
     * Create a new interview with questions
     */
    @PostMapping
    public ResponseEntity<InterviewResponse> createInterview(@Valid @RequestBody InterviewCreationRequest request) {
        InterviewResponse response = interviewService.createInterview(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all interviews by case ID (including questions)
     */
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<InterviewResponse>> getByCaseId(@PathVariable UUID caseId) {
        List<InterviewResponse> responses = interviewService.getInterviewsByCaseId(caseId);
        return ResponseEntity.ok(responses);
    }
    /**
     * Get all interviews by case ID Paging(including questions)
     */
    @GetMapping("/by-case")
    public ResponseEntity<Page<InterviewResponse>> getInterviewsByCaseId(
            @RequestParam UUID caseId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(interviewService.getInterviewsByCaseId(caseId, pageable));
    }

    /**
     * Search and paginate interviews by keyword in location
     */
    @GetMapping
    public ResponseEntity<Page<InterviewResponse>> searchInterviews(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<InterviewResponse> page = interviewService.findAll(keyword, pageable);
        return ResponseEntity.ok(page);
    }
} 