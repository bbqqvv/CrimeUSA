/*
 * @ (#) PermissionRepository.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.repository;


import com.backend.authservice.entity.Permission;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@RepositoryRestResource
@Hidden
public interface PermissionRepository extends JpaRepository<Permission,String> {

}
