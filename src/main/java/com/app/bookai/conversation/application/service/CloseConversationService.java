package com.app.bookai.conversation.application.service;

import com.app.bookai.conversation.domain.enums.ConversationStatus;
import com.app.bookai.conversation.domain.exception.ConversationNotFoundException;
import com.app.bookai.conversation.domain.model.Conversation;
import com.app.bookai.conversation.domain.port.in.CloseConversationUseCase;
import com.app.bookai.conversation.domain.port.out.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloseConversationService implements CloseConversationUseCase {

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation execute(String phoneNumber) {

        Conversation conversation = conversationRepository
                .findByPhoneNumberAndStatus(
                        phoneNumber,
                        ConversationStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new ConversationNotFoundException(phoneNumber)
                );

        conversation.close();

        return conversationRepository.save(conversation);
    }
}