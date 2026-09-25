package com.tharun.videochat.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tharun.videochat.conversation.entity.Conversation;
import com.tharun.videochat.conversation.repository.ConversationParticipantRepository;
import com.tharun.videochat.conversation.repository.ConversationRepository;
import com.tharun.videochat.message.dto.MessageResponse;
import com.tharun.videochat.message.entity.Message;
import com.tharun.videochat.message.repository.MessageRepository;
import com.tharun.videochat.user.entity.User;
import com.tharun.videochat.user.repository.UserRepository;

@Service
public class MessageService {

    private static final int MAX_MESSAGE_LENGTH = 100;

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;
    private final MessageRepository messageRepository;

    public MessageService(
            UserRepository userRepository,
            ConversationRepository conversationRepository,
            MessageRepository messageRepository,
            ConversationParticipantRepository conversationParticipantRepository) {

        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public Conversation findConversation(Long conversationId) {

        return conversationRepository.findById(conversationId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Conversation not found"));
    }

    public User findSender(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));
    }

    public Message createMessage(
            Conversation conversation,
            User sender,
            String messageText) {

        Message message = new Message();

        message.setConversation(conversation);
        message.setSender(sender);
        message.setMessageText(messageText);
        message.setCreatedAt(java.time.LocalDateTime.now());

        return messageRepository.save(message);
    }

    public MessageResponse toMessageResponse(Message message) {

        return new MessageResponse(
                message.getId(),
                message.getConversation().getId(),
                message.getSender().getId(),
                message.getMessageText(),
                message.getCreatedAt()
        );
    }

    public MessageResponse sendMessage(
            Long conversationId,
            Long senderId,
            String messageText) {

        Conversation conversation = findConversation(conversationId);

        validateConversationParticipant(
                conversationId,
                senderId);

        validateMessageText(messageText);

        User sender = findSender(senderId);

        Message message = createMessage(
                conversation,
                sender,
                messageText
        );

        return toMessageResponse(message);
    }

    public List<MessageResponse> getMessageHistory(
            Long conversationId,
            Long userId) {

        findConversation(conversationId);

        validateConversationParticipant(
                conversationId,
                userId);

        List<Message> messages =
                messageRepository.findByConversationIdOrderByCreatedAtAsc(
                        conversationId);

        return messages.stream()
                .map(this::toMessageResponse)
                .toList();
    }

    private void validateConversationParticipant(
            Long conversationId,
            Long userId) {

        boolean isParticipant =
                conversationParticipantRepository
                        .existsByConversationIdAndUserId(
                                conversationId,
                                userId);

        if (!isParticipant) {
            throw new IllegalArgumentException(
                    "User is not a participant in this conversation");
        }
    }

    private void validateMessageText(String messageText) {

        if (messageText == null || messageText.isBlank()) {
            throw new IllegalArgumentException(
                    "Message cannot be blank");
        }

        if (messageText.length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException(
                    "Message cannot exceed 100 characters");
        }
    }
}