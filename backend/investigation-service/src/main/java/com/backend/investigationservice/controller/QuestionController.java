/*
 * @ (#) QuestionController.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.request.QuestionCreationRequest;
import com.backend.investigationservice.dto.request.QuestionUpdateRequest;
import com.backend.investigationservice.dto.response.QuestionResponse;
import com.backend.investigationservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/3/2025
 * @version:    1.0
 */
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionCreationRequest request) {
        QuestionResponse response = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable UUID questionId) {
        QuestionResponse response = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<QuestionResponse> responses = questionService.getAllQuestions();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/interview/{interviewId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByInterview(@PathVariable UUID interviewId) {
        List<QuestionResponse> responses = questionService.getQuestionsByInterview(interviewId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable UUID questionId, @RequestBody QuestionUpdateRequest request) {
        QuestionResponse response = questionService.updateQuestion(questionId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
