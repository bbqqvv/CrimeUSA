package com.example.caseservicebase.service;

import com.backend.caseservice.dto.requestDTO.TimelineRequestDTO;
import com.backend.caseservice.model.Timeline;

import java.util.List;

public interface TimelineService {
    Long createTimeline(TimelineRequestDTO request);
    Timeline getTimelineById(Long id);
    List<Timeline> getAllTimelines();
    Timeline updateTimeline(Long id, TimelineRequestDTO request);
    void softDeleteTimeline(Long id);
}