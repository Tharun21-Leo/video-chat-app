package com.tharun.videochat.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.tharun.videochat.user.dto.LoginRequest;
import com.tharun.videochat.user.dto.LoginResponse;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tharun.videochat.user.dto.RegisterRequest;
import com.tharun.videochat.user.dto.RegisterResponse;
import com.tharun.videochat.user.entity.User;
import com.tharun.videochat.user.repository.UserRepository;

import com.tharun.videochat.exception.InvalidCredentialsException;

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
    
    public LoginResponse login(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByMobileNumber(request.getMobileNumber());

        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("Invalid mobile number or password");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid mobile number or password");
        }

        return new LoginResponse(
                "Login successful",
                user.getId(),
                user.getName(),
                user.getMobileNumber()
        );
    }
}