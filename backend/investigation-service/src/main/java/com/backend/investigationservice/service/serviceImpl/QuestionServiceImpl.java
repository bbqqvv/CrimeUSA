/*
 * @ (#) QuestionServiceImpl.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.investigationservice.service.serviceImpl;

import com.backend.investigationservice.dto.request.QuestionCreationRequest;
import com.backend.investigationservice.dto.request.QuestionUpdateRequest;
import com.backend.investigationservice.dto.response.QuestionResponse;
import com.backend.investigationservice.model.Interview;
import com.backend.investigationservice.model.Question;
import com.backend.investigationservice.repository.InterviewRepository;
import com.backend.investigationservice.repository.QuestionRepository;
import com.backend.investigationservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final InterviewRepository interviewRepository;

    @Override
    public QuestionResponse createQuestion(QuestionCreationRequest request) {
        Interview interview = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        Question question = new Question();
        question.setCreatedBy(request.getCreatedBy());
        question.setContent(request.getContent());
        question.setAnswer(request.getAnswer());
        question.setReliability(request.getReliability());
        question.setInterview(interview);
        question.setDeleted(false);

        Question savedQuestion = questionRepository.save(question);
        return mapToResponse(savedQuestion);
    }

    @Override
    public QuestionResponse getQuestionById(UUID questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return mapToResponse(question);
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        List<Question> questions = questionRepository.findByIsDeletedFalse();
        return questions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponse> getQuestionsByInterview(UUID interviewId) {
        List<Question> questions = questionRepository.findByInterview_InterviewId(interviewId);
        return questions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionResponse updateQuestion(UUID questionId, QuestionUpdateRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (request.getCreatedBy() != null) {
            question.setCreatedBy(request.getCreatedBy());
        }
        if (request.getContent() != null) {
            question.setContent(request.getContent());
        }
        if (request.getAnswer() != null) {
            question.setAnswer(request.getAnswer());
        }
        if (request.getReliability() != null) {
            question.setReliability(request.getReliability());
        }
        if (request.getInterviewId() != null) {
            Interview interview = interviewRepository.findById(request.getInterviewId())
                    .orElseThrow(() -> new RuntimeException("Interview not found"));
            question.setInterview(interview);
        }

        Question updatedQuestion = questionRepository.save(question);
        return mapToResponse(updatedQuestion);
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setDeleted(true);
        questionRepository.save(question);
    }

    private QuestionResponse mapToResponse(Question question) {
        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .createdBy(question.getCreatedBy())
                .content(question.getContent())
                .answer(question.getAnswer())
                .reliability(question.getReliability())
                .interviewId(question.getInterview() != null ? question.getInterview().getInterviewId() : null)
                .deleted(question.isDeleted())
                .build();
    }
}
