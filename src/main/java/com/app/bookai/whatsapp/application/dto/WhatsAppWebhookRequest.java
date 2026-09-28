package com.app.bookai.whatsapp.application.dto;

import java.util.List;

public record WhatsAppWebhookRequest(
        List<Entry> entry
) {

    public record Entry(
            List<Change> changes
    ) {}

    public record Change(
            Value value
    ) {}

    public record Value(
            List<Message> messages
    ) {}

    public record Message(
            String from,
            String type,
            Text text
    ) {}

    public record Text(
            String body
    ) {}
}