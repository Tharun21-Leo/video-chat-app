package com.tharun.videochat.conversation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.videochat.conversation.entity.Conversation;
import com.tharun.videochat.conversation.service.ConversationService;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation startConversation(
            @RequestParam Long userAId,
            @RequestParam Long userBId) {

        return conversationService.startConversation(userAId, userBId);
    }
}