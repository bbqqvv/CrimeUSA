/*
 * @ (#) RoleReposiory.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.repository;


import com.backend.authservice.dto.response.RoleResponse;
import com.backend.authservice.entity.Role;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@RepositoryRestResource
@Hidden
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByRoleId(String roleId);
}
