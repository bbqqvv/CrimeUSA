package com.backend.investigationservice.repository;

import com.backend.investigationservice.model.VictimInterview;
import com.backend.investigationservice.model.VictimInterviewId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VictimInterviewRepository extends JpaRepository<VictimInterview, VictimInterviewId> {
    List<VictimInterview> findByInterview_InterviewId(UUID interviewId);
}
