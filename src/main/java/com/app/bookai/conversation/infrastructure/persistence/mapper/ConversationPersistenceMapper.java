package com.app.bookai.conversation.infrastructure.persistence.mapper;

import com.app.bookai.conversation.domain.model.Conversation;
import com.app.bookai.conversation.infrastructure.persistence.entity.ConversationEntity;
import org.springframework.stereotype.Component;

@Component
public class ConversationPersistenceMapper {

    public ConversationEntity toEntity(Conversation conversation) {

        ConversationEntity entity = new ConversationEntity();

        entity.setId(conversation.getId());
        entity.setPhoneNumber(conversation.getPhoneNumber());
        entity.setStatus(conversation.getStatus());
        entity.setStartedAt(conversation.getStartedAt());
        entity.setLastActivityAt(conversation.getLastActivityAt());
        entity.setClosedAt(conversation.getClosedAt());

        return entity;
    }

    public Conversation toDomain(ConversationEntity entity) {

        return new Conversation(
                entity.getId(),
                entity.getPhoneNumber(),
                entity.getStatus(),
                entity.getStartedAt(),
                entity.getLastActivityAt(),
                entity.getClosedAt()
        );
    }
}