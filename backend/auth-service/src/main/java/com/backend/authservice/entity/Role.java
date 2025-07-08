package com.backend.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String roleId;
    String description;
    @Column(name = "is_deleted", nullable = false)
    boolean isDeleted = false;

    @OneToMany(mappedBy = "role")
    Set<User> users;

    @OneToMany(mappedBy = "role")
    Set<RolePermission> rolePermissions;
}