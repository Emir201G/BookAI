package com.app.bookai.ai.domain.port.in;

public interface ChatUseCase {

    String sendMessage(String message,Long conversationId);
}
