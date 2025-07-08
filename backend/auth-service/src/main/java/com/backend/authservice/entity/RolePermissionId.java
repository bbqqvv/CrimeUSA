/*
 * @ (#) RolePermissionId.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissionId {
    @Column(name = "role_id")
    String roleId;
    @Column(name = "permission_id")
    String permissionId;
}
