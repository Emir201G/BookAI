package com.app.bookai.payment.domain.port.out;

import com.app.bookai.payment.domain.model.Payment;

import java.util.List;

public interface PaymentRepository {
    Payment save(Payment payment);

    List<Payment> getAllPayment();
}
