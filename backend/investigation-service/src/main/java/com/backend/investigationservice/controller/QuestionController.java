/*
 * @ (#) QuestionController.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.investigationservice.controller;

import com.backend.investigationservice.dto.response.QuestionResponse;
import com.backend.investigationservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(questionService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
