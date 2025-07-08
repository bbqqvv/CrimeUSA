package com.example.auth.service;

import com.example.auth.dto.AuthRequest;
import com.example.auth.entity.User;
import com.example.auth.security.JwtService;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String login(AuthRequest request) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        System.out.println("Generated Token: " + token); // ✅ Debug log
        return token;
    }
}