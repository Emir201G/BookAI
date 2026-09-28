package com.app.bookai.conversation.domain.port.out;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import com.app.bookai.conversation.domain.model.Conversation;

import java.util.Optional;

public interface ConversationRepository {
    Optional<Conversation> findByPhoneNumberAndStatus(
            String phoneNumber,
            ConversationStatus status
    );

    Conversation save(Conversation conversation);
}
