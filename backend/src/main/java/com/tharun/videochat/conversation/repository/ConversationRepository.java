package com.tharun.videochat.conversation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharun.videochat.conversation.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}