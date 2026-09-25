package com.tharun.videochat.message.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.videochat.message.dto.MessageResponse;
import com.tharun.videochat.message.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageResponse sendMessage(
            @RequestParam Long conversationId,
            @RequestParam String messageText,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        return messageService.sendMessage(
                conversationId,
                userId,
                messageText
        );
    }

    @GetMapping
    public List<MessageResponse> getMessageHistory(
            @RequestParam Long conversationId,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        return messageService.getMessageHistory(
                conversationId,
                userId
        );
    }
}