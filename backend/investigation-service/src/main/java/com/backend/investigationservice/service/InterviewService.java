package com.backend.investigationservice.service;

import com.backend.investigationservice.dto.request.InterviewCreationRequest;
import com.backend.investigationservice.dto.request.InterviewUpdateRequest;
import com.backend.investigationservice.dto.response.InterviewResponse;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
    InterviewResponse createInterview(InterviewCreationRequest request);
    InterviewResponse getInterviewById(UUID interviewId);
    List<InterviewResponse> getAllInterviews();
    List<InterviewResponse> getInterviewsByInvestigationPlan(UUID investigationPlanId);
    InterviewResponse updateInterview(UUID interviewId, InterviewUpdateRequest request);
    void deleteInterview(UUID interviewId);
} 