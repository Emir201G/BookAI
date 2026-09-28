package com.app.bookai.appointment.domain.model;

import java.math.BigDecimal;

public record PaymentGatewayPaymentResponse (
        String externalPaymentId,
        String externalPreferenceId,
        String status,
        BigDecimal amount
){
}
