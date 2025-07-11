package com.example.caseservicebase.repository;

import com.backend.caseservice.model.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Optional<Timeline> findByIdAndIsDeletedFalse(Long id);
    List<Timeline> findAllByIsDeletedFalse();
}