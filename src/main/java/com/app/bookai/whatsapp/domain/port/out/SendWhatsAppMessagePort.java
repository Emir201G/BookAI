package com.app.bookai.whatsapp.domain.port.out;

public interface SendWhatsAppMessagePort {

    void sendMessage(String phoneNumber, String message);
}