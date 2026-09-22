package com.tharun.videochat.user.dto;

public class LoginResponse {

    private String message;
    private Long userId;
    private String name;
    private String mobileNumber;

    public LoginResponse(String message, Long userId, String name, String mobileNumber) {
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}