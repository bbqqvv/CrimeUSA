package com.example.auth.controller;

import com.example.auth.dto.AuthRequest;
import com.example.auth.dto.AuthResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout success");
    }
}