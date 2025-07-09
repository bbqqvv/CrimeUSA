/*
 * @ (#) UserServiceImpl.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.service.serviceImpl;

import com.backend.authservice.dto.request.UserCreationRequest;
import com.backend.authservice.dto.response.UserResponse;
import com.backend.authservice.entity.User;
import com.backend.authservice.mapper.UserMapper;
import com.backend.authservice.repository.UserRepository;
import com.backend.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    /**
     * Create a new user in the system.
     *
     * @param user The user creation request containing user details.
     * @return The created user response.
     * @throws IllegalArgumentException if a user with the same username already exists.
     */
    @Override
    public UserResponse createUser(UserCreationRequest user) {
        if(userRep.existsUsersByUsername(user.getUsername())) {
            log.error("User with username {} already exists", user.getUsername());
            throw new IllegalArgumentException("User with this username already exists");
        }
        User newUser = userMapper.toUser(user);
        log.info("Creating new user with username: {}", newUser.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        User savedUser = userRep.save(newUser);
        return userMapper.toUserRes(savedUser);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        User user = userRep.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        passwordEncoder.matches(user.getPasswordHash(), user.getPasswordHash());
        return userMapper.toUserRes(user);
    }
}
