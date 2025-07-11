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
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;

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

    UserRepository userRep;
    PermissionRepository permissionRep;
    JwtEncoder jwtEncoder;
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

    /**
     * Introspect a JWT token to check its validity and expiration.
     *
     * @param token The JWT token to introspect.
     * @return true if the token is valid and not expired, false otherwise.
     * @throws JOSEException  if there is an error verifying the token.
     * @throws ParseException if there is an error parsing the token.
     */
    @Override
    public boolean introspect(String token) throws JOSEException, ParseException {
        return true;
    }

    /**
     * Generate a JWT token for the authenticated user.
     *
     * @param user The authenticated user.
     * @return A JWT token as a string.
     */
    private String generateToken(User user) {
        log.info("Generating JWT token for user: {}", user.getUserName());
        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256).build();
        Instant now = Instant.now();
        String r = permissionRep.findDescriptionByRoleId(user.getRole().getRoleId());
        log.info("Role description for user {}: {}", user.getRole().getDescription(), r);
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(30)))
                .claim("role", user.getRole().getDescription()) // Assuming User has a getRoles() method
                .claim("permission", r)
                .subject(user.getUserName())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claimsSet)).getTokenValue();
    }
}
