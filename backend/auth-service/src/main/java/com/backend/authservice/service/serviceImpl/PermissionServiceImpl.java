/*
 * @ (#) PermissionServiceImpl.java  1.0 7/9/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.service.serviceImpl;

import com.backend.authservice.dto.request.PermissionCreationRequest;
import com.backend.authservice.dto.response.PermissionResponse;
import com.backend.authservice.entity.Permission;
import com.backend.authservice.mapper.PermissionMapper;
import com.backend.authservice.repository.PermissionRepository;
import com.backend.authservice.service.PermissionService;
import com.backend.commonservice.enums.ErrorMessage;
import com.backend.commonservice.model.AppException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/9/2025
 * @version:    1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRep;
    PermissionMapper permissionMapper;

    /**
     * Retrieves all permissions that are not deleted.
     *
     * @return a list of PermissionResponse objects representing all non-deleted permissions
     */
    @Override
    public List<PermissionResponse> getAllPermissions() {
      return  permissionRep.findAll()
                .stream()
                .map(permissionMapper::toPerRes)
                .toList();
    }

    /**
     * Creates a new permission.
     *
     * @param per the permission creation request
     * @return the created permission response
     * @throws AppException if the permission already exists
     */
    @Transactional
    @Override
    public PermissionResponse createPermission(PermissionCreationRequest per) {

    Permission permission = permissionMapper.toPer(per);
    // Check if the permission already exists
    if (permissionRep.existsByDescription(permission.getDescription())) {
        throw new AppException(ErrorMessage.PERMISSION_ALREADY_EXISTS);
    }
        // Save the new permission
    return permissionMapper.toPerRes(permissionRep.save(permission));
    }

    @Override
    public void deletePermission(String permissionId) {
        permissionRep.deletePermissionByPermissionId(permissionId)
                .orElseThrow(() -> new AppException(ErrorMessage.PERMISSION_NOT_FOUND));
    }
}
