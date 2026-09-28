package com.app.bookai.conversation.domain.model;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Conversation {

    private Long id;
    private String phoneNumber;
    private ConversationStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime lastActivityAt;
    private LocalDateTime closedAt;

    public Conversation(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.status = ConversationStatus.ACTIVE;
        this.startedAt = LocalDateTime.now();
        this.lastActivityAt = LocalDateTime.now();
    }

    public Conversation(
            Long id,
            String phoneNumber,
            ConversationStatus status,
            LocalDateTime startedAt,
            LocalDateTime lastActivityAt,
            LocalDateTime closedAt
    ) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.startedAt = startedAt;
        this.lastActivityAt = lastActivityAt;
        this.closedAt = closedAt;
    }

    public void updateActivity() {
        this.lastActivityAt = LocalDateTime.now();
    }

    public void close() {
        if (this.status == ConversationStatus.CLOSED) {
            return;
        }

        this.status = ConversationStatus.CLOSED;
        this.closedAt = LocalDateTime.now();
    }
}