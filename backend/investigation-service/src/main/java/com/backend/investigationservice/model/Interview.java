package com.backend.investigationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "interviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "interview_id", nullable = false)
    private UUID interviewId;

    @ManyToOne
    @JoinColumn(name = "investigation_plan_id")
    private InvestigationPlan investigationPlan;

    @Column(name = "location")
    private String location;

    @Column(name = "attached_file")
    private String attachedFile;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "holiday_conflict")
    private String holidayConflict;

    @Column(name = "holiday_id")
    private String holidayId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<WitnessInterview> witnessInterviews;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<VictimInterview> victimInterviews;
}
