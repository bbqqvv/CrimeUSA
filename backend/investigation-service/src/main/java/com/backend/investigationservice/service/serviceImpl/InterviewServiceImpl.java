package com.backend.investigationservice.service.serviceImpl;

import com.backend.investigationservice.dto.request.InterviewCreationRequest;
import com.backend.investigationservice.dto.request.InterviewUpdateRequest;
import com.backend.investigationservice.dto.request.QuestionCreationRequest;
import com.backend.investigationservice.dto.response.InterviewResponse;
import com.backend.investigationservice.dto.response.QuestionResponse;
import com.backend.investigationservice.model.InvestigationPlan;
import com.backend.investigationservice.model.Interview;
import com.backend.investigationservice.model.Question;
import com.backend.investigationservice.repository.InvestigationPlanRepository;
import com.backend.investigationservice.repository.InterviewRepository;
import com.backend.investigationservice.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InvestigationPlanRepository investigationPlanRepository;

    @Override
    @Transactional
    public InterviewResponse createInterview(InterviewCreationRequest request) {
        InvestigationPlan investigationPlan = investigationPlanRepository.findById(request.getInvestigationPlanId())
                .orElseThrow(() -> new RuntimeException("Investigation plan not found"));

        Interview interview = new Interview();
        interview.setInvestigationPlan(investigationPlan);
        interview.setLocation(request.getLocation());
        interview.setAttachedFile(request.getAttachedFile());
        interview.setStartTime(request.getStartTime());
        interview.setEndTime(request.getEndTime());
        interview.setHolidayConflict(request.getHolidayConflict());
        interview.setHolidayId(request.getHolidayId());
        interview.setDeleted(false);

        Interview savedInterview = interviewRepository.save(interview);
        return mapToResponse(savedInterview);
    }

    @Override
    public InterviewResponse getInterviewById(UUID interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        return mapToResponse(interview);
    }

    @Override
    public List<InterviewResponse> getAllInterviews() {
        List<Interview> interviews = interviewRepository.findByIsDeletedFalse();
        return interviews.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewResponse> getInterviewsByInvestigationPlan(UUID investigationPlanId) {
        List<Interview> interviews = interviewRepository.findByInvestigationPlan_InvestigationPlanIdAndIsDeletedFalse(investigationPlanId);
        return interviews.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewResponse updateInterview(UUID interviewId, InterviewUpdateRequest request) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        if (request.getInvestigationPlanId() != null) {
            InvestigationPlan investigationPlan = investigationPlanRepository.findById(request.getInvestigationPlanId())
                    .orElseThrow(() -> new RuntimeException("Investigation plan not found"));
            interview.setInvestigationPlan(investigationPlan);
        }

        if (request.getLocation() != null) {
            interview.setLocation(request.getLocation());
        }
        if (request.getAttachedFile() != null) {
            interview.setAttachedFile(request.getAttachedFile());
        }
        if (request.getStartTime() != null) {
            interview.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            interview.setEndTime(request.getEndTime());
        }
        if (request.getHolidayConflict() != null) {
            interview.setHolidayConflict(request.getHolidayConflict());
        }
        if (request.getHolidayId() != null) {
            interview.setHolidayId(request.getHolidayId());
        }

        Interview updatedInterview = interviewRepository.save(interview);
        return mapToResponse(updatedInterview);
    }

    @Override
    public void deleteInterview(UUID interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        interview.setDeleted(true);
        interviewRepository.save(interview);
    }

    private InterviewResponse mapToResponse(Interview interview) {
        return InterviewResponse.builder()
                .interviewId(interview.getInterviewId())
                .investigationPlanId(interview.getInvestigationPlan() != null ? interview.getInvestigationPlan().getInvestigationPlanId() : null)
                .location(interview.getLocation())
                .attachedFile(interview.getAttachedFile())
                .startTime(interview.getStartTime())
                .endTime(interview.getEndTime())
                .holidayConflict(interview.getHolidayConflict())
                .holidayId(interview.getHolidayId())
                .deleted(interview.isDeleted())
                .questions(interview.getQuestions() != null ? interview.getQuestions().stream().map(this::mapToQuestionResponse).collect(Collectors.toList()) : null)
                .build();
    }

    private QuestionResponse mapToQuestionResponse(Question question) {
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