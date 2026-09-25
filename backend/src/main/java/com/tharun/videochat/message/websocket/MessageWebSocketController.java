package com.tharun.videochat.message.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tharun.videochat.message.dto.ChatMessageRequest;
import com.tharun.videochat.message.dto.MessageResponse;
import com.tharun.videochat.message.service.MessageService;

@Controller
public class MessageWebSocketController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageWebSocketController(
            MessageService messageService,
            SimpMessagingTemplate messagingTemplate) {

        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendMessage(
            ChatMessageRequest request,
            SimpMessageHeaderAccessor headerAccessor) {

        Object userIdAttribute =
                headerAccessor.getSessionAttributes().get("userId");

        Long userId = (Long) userIdAttribute;

        System.out.println("WebSocket userId: " + userId);

        try {

            MessageResponse response = messageService.sendMessage(
                    request.getConversationId(),
                    userId,
                    request.getMessageText()
            );

            String destination =
                    "/topic/conversation/" + request.getConversationId();

            messagingTemplate.convertAndSend(destination, response);

        } catch (IllegalArgumentException exception) {

            System.out.println(
                    "WebSocket message rejected: "
                    + exception.getMessage());
        }
    }
}