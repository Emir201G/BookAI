package com.app.bookai.conversation.infrastructure.persistence.repository;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import com.app.bookai.conversation.infrastructure.persistence.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaConversationRepository extends JpaRepository<ConversationEntity, Long> {

    Optional<ConversationEntity> findByPhoneNumberAndStatus(
            String phoneNumber,
            ConversationStatus status
    );
}
