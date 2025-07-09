/*
 * @ (#) SecurityConfig.java  1.0 7/9/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.configs;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/9/2025
 * @version:    1.0
 */
@Configuration
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

    @NonFinal // This field not initialized in constructor
    @Value("${jwt.secret-key}")
    String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );
        return http.build();
    }

    /*
     * Creates a JwtDecoder bean using the secret key for JWT validation.
     *
     * @return JwtDecoder instance
     */
    @Bean
    JwtDecoder jwtDecoder() {
        log.info("Creating JWT Decoder with secret key");
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "HS256");
        return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(
                        MacAlgorithm.HS256)
                .build();
    }

    /*
     * Creates a PasswordEncoder bean using BCrypt with a strength of 10.
     *
     * @return PasswordEncoder instance
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        log.info("Creating Password Encoder");
        return new BCryptPasswordEncoder(10);
    }

    /*
     * Customizes the JWT authentication converter to use a prefix for roles.
     *
     * @return JwtAuthenticationConverter with custom role prefix
     */
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        log.info("Creating JWT Authentication Converter");
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("role");
        converter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jconverter = new JwtAuthenticationConverter();
        jconverter.setJwtGrantedAuthoritiesConverter(converter);
        return jconverter;
    }
}
