package com.app.bookai.ai.domain.port.out;

public interface AIChatPort {
    String generateResponse(String message,Long conversationId);
}
