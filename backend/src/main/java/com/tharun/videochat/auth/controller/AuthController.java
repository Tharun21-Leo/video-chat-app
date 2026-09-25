package com.tharun.videochat.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.tharun.videochat.auth.service.AuthService;
import com.tharun.videochat.user.dto.RegisterRequest;
import com.tharun.videochat.user.dto.RegisterResponse;

import com.tharun.videochat.user.dto.LoginRequest;
import com.tharun.videochat.user.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session) {

        LoginResponse response = authService.login(request);

        session.setAttribute("userId", response.getUserId());

        return ResponseEntity.ok(response);
    }
    
}