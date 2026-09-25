package com.tharun.videochat.message.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private Long messageId;
    private Long conversationId;
    private Long senderId;
    private String messageText;
    private LocalDateTime createdAt;

    public MessageResponse(
            Long messageId,
            Long conversationId,
            Long senderId,
            String messageText,
            LocalDateTime createdAt) {

        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.messageText = messageText;
        this.createdAt = createdAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}