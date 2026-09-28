package com.app.bookai.whatsapp.domain.port.in;

public interface SendWhatsAppMessageUseCase {
    void sendMessage(String phoneNumber, String message);

}
