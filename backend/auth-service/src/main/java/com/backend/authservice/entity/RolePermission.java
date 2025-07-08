/*
 * @ (#) RolePermission.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@Entity
@Table(name = "roles_permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermission {
    @EmbeddedId
    RolePermissionId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    Permission permission;

    @Column(name = "is_deleted", nullable = false)
    boolean isDeleted = false;
}
