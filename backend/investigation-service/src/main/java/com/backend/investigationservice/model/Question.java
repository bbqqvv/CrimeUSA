package com.backend.investigationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "content")
    private String content;

    @Column(name = "answer")
    private String answer;

    @Column(name = "reliability")
    private String reliability;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
