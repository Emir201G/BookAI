package com.app.bookai.payment.domain.port.in;

public interface ProcessPaymentWebhookUseCase {

    void process(String externalPaymentId);
}
