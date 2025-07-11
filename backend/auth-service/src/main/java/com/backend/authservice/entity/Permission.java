package com.backend.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "permission_id")
    String permissionId;
    @Column(name = "description", columnDefinition = "TEXT")
    String description;
    @Column(name = "is_deleted", nullable = false)
    boolean isDeleted = false;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;
}