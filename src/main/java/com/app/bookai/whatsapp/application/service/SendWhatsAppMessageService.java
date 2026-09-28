package com.app.bookai.whatsapp.application.service;

import com.app.bookai.whatsapp.domain.port.in.SendWhatsAppMessageUseCase;
import com.app.bookai.whatsapp.domain.port.out.SendWhatsAppMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendWhatsAppMessageService implements SendWhatsAppMessageUseCase {

    private final SendWhatsAppMessagePort  sendWhatsAppMessagePort;
    @Override
    public void sendMessage(String phoneNumber, String message) {
        sendWhatsAppMessagePort.sendMessage(phoneNumber, message);
    }
}
