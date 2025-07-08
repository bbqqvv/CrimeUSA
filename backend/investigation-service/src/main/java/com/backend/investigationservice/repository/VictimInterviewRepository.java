package com.backend.investigationservice.repository;

import com.backend.investigationservice.model.VictimInterview;
import com.backend.investigationservice.model.VictimInterviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VictimInterviewRepository extends JpaRepository<VictimInterview, VictimInterviewId> {
    List<VictimInterview> findByInterview_InterviewId(UUID interviewId);
}
