package com.app.bookai.conversation.infrastructure.persistence.adapter;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import com.app.bookai.conversation.domain.model.Conversation;
import com.app.bookai.conversation.domain.port.out.ConversationRepository;
import com.app.bookai.conversation.infrastructure.persistence.entity.ConversationEntity;
import com.app.bookai.conversation.infrastructure.persistence.mapper.ConversationPersistenceMapper;
import com.app.bookai.conversation.infrastructure.persistence.repository.JpaConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConversationRepositoryAdapter implements ConversationRepository {

    private final JpaConversationRepository conversationJpaRepository;
    private final ConversationPersistenceMapper conversationMapper;

    @Override
    public Optional<Conversation> findByPhoneNumberAndStatus(
            String phoneNumber,
            ConversationStatus status
    ) {

        return conversationJpaRepository
                .findByPhoneNumberAndStatus(phoneNumber, status)
                .map(conversationMapper::toDomain);
    }

    @Override
    public Conversation save(Conversation conversation) {

        ConversationEntity entity =
                conversationMapper.toEntity(conversation);

        ConversationEntity savedEntity =
                conversationJpaRepository.save(entity);

        return conversationMapper.toDomain(savedEntity);
    }
}