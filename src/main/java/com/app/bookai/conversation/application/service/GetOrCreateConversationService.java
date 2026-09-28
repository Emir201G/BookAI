package com.app.bookai.conversation.application.service;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import com.app.bookai.conversation.domain.model.Conversation;
import com.app.bookai.conversation.domain.port.in.GetOrCreateConversationUseCase;
import com.app.bookai.conversation.domain.port.out.ConversationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrCreateConversationService implements GetOrCreateConversationUseCase {

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation execute(String phoneNumber) {

        return conversationRepository
                .findByPhoneNumberAndStatus(
                        phoneNumber,
                        ConversationStatus.ACTIVE
                )
                .map(this::updateActivity)
                .orElseGet(
                        () -> createConversation(phoneNumber)
                );
    }

    private Conversation updateActivity(Conversation conversation) {

        conversation.updateActivity();

        return conversationRepository.save(conversation);
    }

    private Conversation createConversation(String phoneNumber) {

        Conversation conversation = new Conversation(phoneNumber);

        return conversationRepository.save(conversation);
    }
}
