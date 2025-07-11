package com.example.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
public class Permission {
    @Id
    private Long permissionId;
    private String description;
    private boolean isDeleted;
}