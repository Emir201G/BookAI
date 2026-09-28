package com.app.bookai.payment.domain.port.in;

import com.app.bookai.payment.domain.model.Payment;

import java.util.List;

public interface GetAllPaymentUseCase {

    List<Payment> getAllPaymentUseCase();
}
