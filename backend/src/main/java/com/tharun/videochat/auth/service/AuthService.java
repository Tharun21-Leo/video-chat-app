package com.tharun.videochat.auth.service;

import java.time.LocalDateTime;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tharun.videochat.user.dto.RegisterRequest;
import com.tharun.videochat.user.dto.RegisterResponse;
import com.tharun.videochat.user.entity.User;
import com.tharun.videochat.user.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) 
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
        	throw new IllegalStateException("Mobile number is already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                "User registered successfully",
                savedUser.getId(),
                savedUser.getMobileNumber()
        );
    }
}