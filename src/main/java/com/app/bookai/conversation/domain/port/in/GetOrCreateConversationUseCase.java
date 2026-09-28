package com.app.bookai.conversation.domain.port.in;

import com.app.bookai.conversation.domain.model.Conversation;

public interface GetOrCreateConversationUseCase {
    Conversation execute(String phoneNumber);
}
