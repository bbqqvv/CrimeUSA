/*
 * @ (#) UserServiceImpl.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.service.serviceImpl;

import com.backend.authservice.dto.request.UserCreationRequest;
import com.backend.authservice.dto.response.UserResponse;
import com.backend.authservice.entity.Role;
import com.backend.authservice.entity.User;
import com.backend.authservice.mapper.UserMapper;
import com.backend.authservice.repository.RoleRepository;
import com.backend.authservice.repository.UserRepository;
import com.backend.authservice.service.UserService;
import com.backend.commonservice.enums.ErrorMessage;
import com.backend.commonservice.model.AppException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRep;
    UserMapper userMapper;
    RoleRepository roleRep;
    PasswordEncoder passwordEncoder;

    /**
     * Create a new user in the system.
     *
     * @param request The user creation request containing user details.
     * @return The created user response.
     * @throws AppException if the username already exists or the role is not found.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        // Check if the username already exists
        if (userRep.existsUsersByUserName(request.getUserName())) {
            log.error("User with username {} already exists", request.getUserName());
            throw new AppException(ErrorMessage.USERNAME_ALREADY_EXISTS);
        }
        // Check if the role exists
        Role role = roleRep.findRoleByRoleId(request.getRoleId())
                .orElseThrow(() -> new AppException(ErrorMessage.ROLE_NOT_FOUND));
        // Encode the password and create the user
        request.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));

        // Map the request to a User entity and set the role
        User newUser = userMapper.toUser(request);
        log.info("Creating new user with username: {}", newUser);
        newUser.setRole(role);
        User savedUser = userRep.save(newUser);
        return userMapper.toUserRes(savedUser);
    }

    /**
     * Get user details by username.
     *
     * @param username The username of the user to retrieve.
     * @return The user response containing user details.
     * @throws AppException if the user is not found.
     */
    @Override
    public UserResponse getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        User user = userRep.findUserByUserName(username)
                .orElseThrow(() -> new AppException(ErrorMessage.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        passwordEncoder.matches(user.getPasswordHash(), user.getPasswordHash());
        return userMapper.toUserRes(user);
    }

    @PreAuthorize("hasAuthority('ALL')")
    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userRep.getAllByIsDeletedFalse()
                .stream()
                .map(userMapper::toUserRes)
                .toList();
    }
    @PreAuthorize("hasAuthority('AL')")
    @Override
    public UserResponse getMyInfo() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return getUserByUsername(username);
    }
}
