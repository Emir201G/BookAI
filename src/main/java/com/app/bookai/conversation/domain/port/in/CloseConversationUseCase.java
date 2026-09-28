package com.app.bookai.conversation.domain.port.in;

import com.app.bookai.conversation.domain.model.Conversation;

public interface CloseConversationUseCase {
    Conversation execute(String phoneNumber);
}
