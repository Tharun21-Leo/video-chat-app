package com.tharun.videochat.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.videochat.user.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tharun.videochat.user.dto.UserSearchResponse;
import com.tharun.videochat.user.entity.User;

import com.tharun.videochat.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/search")
    public UserSearchResponse searchUser(
            @RequestParam String mobileNumber) {

        User user = userService.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserSearchResponse(
                user.getId(),
                user.getName(),
                user.getMobileNumber()
        );
    }
}