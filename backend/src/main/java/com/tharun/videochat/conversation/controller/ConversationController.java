package com.tharun.videochat.conversation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.videochat.conversation.entity.Conversation;
import com.tharun.videochat.conversation.service.ConversationService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation startConversation(
            @RequestParam Long userBId,
            HttpSession session) {

        Long userAId = (Long) session.getAttribute("userId");

        return conversationService.startConversation(
                userAId,
                userBId
        );
    }
}