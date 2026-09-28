package com.app.bookai.whatsapp.domain.port.in;

public interface ProcessIncomingWhatsAppMessageUseCase {

    void process(String phoneNumber, String message);
}
