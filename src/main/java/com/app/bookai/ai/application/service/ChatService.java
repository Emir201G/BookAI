package com.app.bookai.ai.application.service;

import com.app.bookai.ai.domain.port.in.ChatUseCase;
import com.app.bookai.ai.domain.port.out.AIChatPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService implements ChatUseCase {

    private final AIChatPort  aiChatPort;
    @Override
    public String sendMessage(String message,Long conversationId) {
        return aiChatPort.generateResponse(message,conversationId);
    }
}
