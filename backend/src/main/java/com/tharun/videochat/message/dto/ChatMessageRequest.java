package com.tharun.videochat.message.dto;

public class ChatMessageRequest {

    private Long conversationId;
    //private Long senderId;
    private String messageText;

    public ChatMessageRequest() {
    }

    public Long getConversationId() {
        return conversationId;
    }

   /* public Long getSenderId() {
        return senderId;
    }*/

    public String getMessageText() {
        return messageText;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    /*public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }*/

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}