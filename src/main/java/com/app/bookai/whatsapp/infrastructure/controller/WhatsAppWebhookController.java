package com.app.bookai.whatsapp.infrastructure.controller;

import com.app.bookai.whatsapp.application.dto.WhatsAppWebhookRequest;
import com.app.bookai.whatsapp.domain.port.in.ProcessIncomingWhatsAppMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/whatsapp/webhook")

public class WhatsAppWebhookController {

    private final String verifyToken;
    private final ProcessIncomingWhatsAppMessageUseCase processIncomingWhatsAppMessageUseCase;

    public WhatsAppWebhookController(
            @Value("${whatsapp.webhook.verify-token}") String verifyToken,
            ProcessIncomingWhatsAppMessageUseCase processIncomingWhatsAppMessageUseCase) {

        this.verifyToken = verifyToken;
        this.processIncomingWhatsAppMessageUseCase = processIncomingWhatsAppMessageUseCase;
    }

    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            return ResponseEntity.ok(challenge);
        }

        return ResponseEntity.status(403).body("Forbidden");
    }

    @PostMapping
    public String receiveMessage(@RequestBody WhatsAppWebhookRequest request) {

        var message = request.entry()
                .get(0)
                .changes()
                .get(0)
                .value()
                .messages()
                .get(0);

        System.out.println("FROM: " + message.from());
        System.out.println("MESSAGE: " + message.text().body());

        processIncomingWhatsAppMessageUseCase.process(
                message.from(),
                message.text().body()
        );
        return "EVENT_RECEIVED";
    }
}