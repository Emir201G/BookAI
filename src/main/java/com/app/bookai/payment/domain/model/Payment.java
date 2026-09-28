package com.app.bookai.payment.domain.model;

import com.app.bookai.payment.domain.enums.PaymentMethod;
import com.app.bookai.payment.domain.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private Long id;
    private Long appointmentId;
    private BigDecimal amount;

    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;

//    private PaymentMethod method;

    private String externalPreferenceId;
    private String externalPaymentId;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}