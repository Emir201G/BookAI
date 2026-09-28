package com.app.bookai.whatsapp.application.service;

import com.app.bookai.ai.domain.port.in.ChatUseCase;
import com.app.bookai.conversation.domain.model.Conversation;
import com.app.bookai.conversation.domain.port.in.GetOrCreateConversationUseCase;
import com.app.bookai.user.domain.port.in.IdentifyUserUseCase;
import com.app.bookai.whatsapp.domain.port.in.ProcessIncomingWhatsAppMessageUseCase;
import com.app.bookai.whatsapp.domain.port.in.SendWhatsAppMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessIncomingWhatsAppMessageService implements ProcessIncomingWhatsAppMessageUseCase {

    private final ChatUseCase chatUseCase;
    private final SendWhatsAppMessageUseCase sendWhatsAppMessageUseCase;
    private final GetOrCreateConversationUseCase getOrCreateConversationUseCase;
    private final IdentifyUserUseCase identifyUserUseCase;

    @Override
    public void process(String phoneNumber, String message) {

        identifyUserUseCase.identify(phoneNumber);

        Conversation conversation =
                getOrCreateConversationUseCase.execute(phoneNumber);

        String response = chatUseCase.sendMessage(
                message,
                conversation.getId()
        );

        sendWhatsAppMessageUseCase.sendMessage(
                phoneNumber,
                response
        );
    }
}
