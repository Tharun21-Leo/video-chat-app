package com.tharun.videochat.user.dto;

public class UserSearchResponse {

    private Long userId;
    private String name;
    private String mobileNumber;

    public UserSearchResponse(Long userId, String name, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
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