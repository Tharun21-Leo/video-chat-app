package com.tharun.videochat.conversation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tharun.videochat.conversation.entity.Conversation;
import com.tharun.videochat.conversation.entity.ConversationParticipant;

public interface ConversationParticipantRepository
        extends JpaRepository<ConversationParticipant, Long> {

    @Query("""
            SELECT cp1.conversation
            FROM ConversationParticipant cp1
            JOIN ConversationParticipant cp2
                ON cp1.conversation.id = cp2.conversation.id
            WHERE cp1.user.id = :userAId
              AND cp2.user.id = :userBId
            """)
    Optional<Conversation> findConversationBetweenUsers(
            @Param("userAId") Long userAId,
            @Param("userBId") Long userBId
    );
}
