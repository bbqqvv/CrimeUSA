/*
 * @ (#) QuestionService.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.investigationservice.service;

import com.backend.investigationservice.dto.request.QuestionCreationRequest;
import com.backend.investigationservice.dto.request.QuestionUpdateRequest;
import com.backend.investigationservice.dto.response.QuestionResponse;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    QuestionResponse createQuestion(QuestionCreationRequest request);
    QuestionResponse getQuestionById(UUID questionId);
    List<QuestionResponse> getAllQuestions();
    List<QuestionResponse> getQuestionsByInterview(UUID interviewId);
    QuestionResponse updateQuestion(UUID questionId, QuestionUpdateRequest request);
    void deleteQuestion(UUID questionId);
}
