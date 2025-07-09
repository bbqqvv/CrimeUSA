/*
 * @ (#) AuthServiceImpl.java  1.0 7/9/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.service.serviceImpl;

import com.backend.authservice.entity.User;
import com.backend.authservice.repository.PermissionRepository;
import com.backend.authservice.repository.UserRepository;
import com.backend.authservice.service.AuthService;
import com.backend.commonservice.enums.ErrorMessage;
import com.backend.commonservice.model.AppException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class AuthServiceImpl implements AuthService {

    @NonFinal // This field not initialized in constructor
    @Value("${jwt.secret-key}")
    String secretKey;
    UserRepository userRep;
    PermissionRepository permissionRep;
    PasswordEncoder passwordEncoder;


    /**
     * Authenticate a user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A JWT token if authentication is successful.
     * @throws AppException if the user is not found or if the credentials are invalid.
     */
    @Override
    public String authenticate(String username, String password) {
        log.info("Authenticating user with username: {}", username);
        User user = userRep.findUserByUserName(username)
                .orElseThrow(() -> new AppException(ErrorMessage.USER_NOT_FOUND));
        boolean result = passwordEncoder.matches(password, user.getPasswordHash());
        if (!result) {
            log.error("Authentication failed for user: {}", username);
            throw new AppException(ErrorMessage.INVALID_CREDENTIALS);
        }
        log.info("Authentication successful for user: {}", username);
        return generateToken(user);
    }

    private String generateToken(User user) {
        log.info("Generating JWT token for user: {}", user.getUserName());
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        Optional<String> r = permissionRep.findDescriptionByRoleId(user.getRole().getRoleId());
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .claim("role", user.getRole().getDescription()) // Assuming User has a getRoles() method
                .claim("permission", r.orElse(null))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error generating JWT token: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
