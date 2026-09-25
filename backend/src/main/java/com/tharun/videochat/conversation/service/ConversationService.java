package com.tharun.videochat.conversation.service;

import org.springframework.stereotype.Service;

import com.tharun.videochat.conversation.repository.ConversationParticipantRepository;
import com.tharun.videochat.conversation.repository.ConversationRepository;
import com.tharun.videochat.user.repository.UserRepository;

import com.tharun.videochat.conversation.entity.Conversation;

import com.tharun.videochat.conversation.entity.ConversationParticipant;
import com.tharun.videochat.user.entity.User;

@Service
public class ConversationService {

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;

    public ConversationService(
            UserRepository userRepository,
            ConversationRepository conversationRepository,
            ConversationParticipantRepository conversationParticipantRepository) 
    {

        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }
    
    public void validateUsers(Long userAId, Long userBId) {

        userRepository.findById(userAId)
                .orElseThrow(() -> new IllegalArgumentException("User A not found"));

        userRepository.findById(userBId)
                .orElseThrow(() -> new IllegalArgumentException("User B not found"));
    }
    
    public Conversation findExistingConversation(Long userAId, Long userBId) {

        return conversationParticipantRepository
                .findConversationBetweenUsers(userAId, userBId)
                .orElse(null);
    }
    
    public Conversation createConversation(Long userAId, Long userBId) {

        Conversation conversation = new Conversation();
        conversation.setCreatedAt(java.time.LocalDateTime.now());

        return conversationRepository.save(conversation);
    }
    
    public void addParticipant(Conversation conversation, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ConversationParticipant participant = new ConversationParticipant();
        participant.setConversation(conversation);
        participant.setUser(user);

        conversationParticipantRepository.save(participant);
    }
    
    public Conversation startConversation(Long userAId, Long userBId) {

        validateUsers(userAId, userBId);

        if (userAId.equals(userBId)) {
            throw new IllegalArgumentException(
                    "Cannot start a conversation with yourself");
        }

        Conversation existingConversation =
                findExistingConversation(userAId, userBId);

        if (existingConversation != null) {
            return existingConversation;
        }

        Conversation conversation =
                createConversation(userAId, userBId);

        addParticipant(conversation, userAId);
        addParticipant(conversation, userBId);

        return conversation;
    }
}