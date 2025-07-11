/*
 * @ (#) RoleImpl.java  1.0 7/9/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.service.serviceImpl;

import com.backend.authservice.dto.request.RoleCreationRequest;
import com.backend.authservice.dto.response.RoleResponse;
import com.backend.authservice.entity.Role;
import com.backend.authservice.mapper.RoleMapper;
import com.backend.authservice.repository.PermissionRepository;
import com.backend.authservice.repository.RoleRepository;
import com.backend.authservice.service.RoleService;
import com.backend.commonservice.enums.ErrorMessage;
import com.backend.commonservice.model.AppException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/9/2025
 * @version:    1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRep;
    RoleMapper roleMapper;
    PermissionRepository permissionRep;

    /**
     * Get role by role ID.
     *
     * @param roleId The ID of the role to retrieve.
     * @return The role response containing role details.
     */
    @Override
    public RoleResponse getRoleByRoleId(String roleId) {
        log.info("Fetching role with ID: {}", roleId);
        Role role = roleRep.findRoleByRoleId(roleId)
                .orElseThrow(() -> new AppException(ErrorMessage.ROLE_NOT_FOUND));
        return roleMapper.toRoleResponse(role);
    }

    /**
     * Get all roles in the system.
     *
     * @return A list of role responses containing details of all roles.
     */
    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRep.findAll().stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    /**
     * Create a new role with the specified permissions.
     *
     * @param roleCreationRequest The request containing role details and permissions.
     * @return The created role response.
     */
    @Transactional
    @Override
    public RoleResponse createRole(RoleCreationRequest roleCreationRequest) {
        Role role = roleMapper.toRole(roleCreationRequest);
        var permissions = permissionRep.findAllById(roleCreationRequest.getPermissions());
        if (permissions.isEmpty()) {
            throw new AppException(ErrorMessage.PERMISSION_NOT_FOUND);
        }
        role.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(roleRep.save(role));
    }
}
