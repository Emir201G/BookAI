package com.app.bookai.conversation.domain.exception;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(String phoneNumber) {
        super("Conversation Not Found: " + phoneNumber);
    }
}
