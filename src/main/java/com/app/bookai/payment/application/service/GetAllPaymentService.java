package com.app.bookai.payment.application.service;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.GetAllAppointmentUseCase;
import com.app.bookai.payment.domain.model.Payment;
import com.app.bookai.payment.domain.port.in.GetAllPaymentUseCase;
import com.app.bookai.payment.domain.port.out.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPaymentService implements GetAllPaymentUseCase {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPaymentUseCase() {
        return paymentRepository.getAllPayment();
    }


}
