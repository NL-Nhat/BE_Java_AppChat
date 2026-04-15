package com.example.chatMulticat.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.chatMulticat.dto.request.LoginRequest;
import com.example.chatMulticat.dto.request.RegisterRequest;
import com.example.chatMulticat.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        
        return ResponseEntity.ok(authService.register(registerRequest));
    }
    
    
}
