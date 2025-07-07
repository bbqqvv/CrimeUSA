package com.backend.investigationservice.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "witness_interviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WitnessInterview {

    @EmbeddedId
    private WitnessInterviewId id;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    // Optional: mapping đến Interview (nếu cần truy xuất thông tin)
    @ManyToOne
    @MapsId("interviewId")
    @JoinColumn(name = "interview_id", insertable = false, updatable = false)
    private Interview interview;
}

