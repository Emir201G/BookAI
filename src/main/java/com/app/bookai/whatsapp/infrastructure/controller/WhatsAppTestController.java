package com.app.bookai.whatsapp.infrastructure.controller;

import com.app.bookai.whatsapp.domain.port.in.SendWhatsAppMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
@RequiredArgsConstructor
public class WhatsAppTestController {

    private final SendWhatsAppMessageUseCase  sendWhatsAppMessageUseCase;

    @PostMapping("/test")
    public void sendTestMessage(
            @RequestParam String phoneNumber,
            @RequestParam String message) {

        sendWhatsAppMessageUseCase.sendMessage(
                phoneNumber,
                message
        );
    }
}
