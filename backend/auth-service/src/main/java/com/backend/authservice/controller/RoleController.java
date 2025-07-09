/*
 * @ (#) RoleController.java  1.0 7/9/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.controller;

import com.backend.authservice.dto.request.RoleCreationRequest;
import com.backend.authservice.dto.response.RoleResponse;
import com.backend.authservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/9/2025
 * @version:    1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Role Query", description = "Role API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RoleController {
    RoleService roleService;

    /*
     * @method: Get role by roleId
     * @description: Returns the role information for the given roleId.
     */
    @Operation(summary = "Get role by roleId"
            , description = "Returns the role information for the given roleId."
    )
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        log.info("Fetching all roles");
        List<RoleResponse> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Create a new role"
            , description = "Create a new role with the provided details."
    )
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody @Valid RoleCreationRequest roleCreationRequest) {
        log.info("Creating new role with name: {}", roleCreationRequest.getDescription());
        RoleResponse createdRole = roleService.createRole(roleCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

}
