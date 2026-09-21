package com.tharun.videochat.user.dto;

public class RegisterResponse {

    private String message;
    private Long userId;
    private String mobileNumber;

    public RegisterResponse(String message, Long userId, String mobileNumber) {
        this.message = message;
        this.userId = userId;
        this.mobileNumber = mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}