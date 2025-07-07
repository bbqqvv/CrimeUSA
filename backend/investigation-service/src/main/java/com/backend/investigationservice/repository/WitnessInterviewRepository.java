package com.backend.investigationservice.repository;

import com.backend.investigationservice.model.WitnessInterview;
import com.backend.investigationservice.model.WitnessInterviewId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WitnessInterviewRepository extends JpaRepository<WitnessInterview, WitnessInterviewId> {
    List<WitnessInterview> findByInterview_InterviewId(UUID interviewId);
}
