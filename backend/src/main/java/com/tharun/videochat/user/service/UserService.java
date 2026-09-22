package com.tharun.videochat.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tharun.videochat.user.entity.User;
import com.tharun.videochat.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByMobileNumber(String mobileNumber) {

        return userRepository.findByMobileNumber(mobileNumber);
    }
}