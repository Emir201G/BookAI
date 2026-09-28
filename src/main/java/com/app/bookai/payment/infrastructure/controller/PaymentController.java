package com.app.bookai.payment.infrastructure.controller;

import com.app.bookai.payment.domain.model.Payment;
import com.app.bookai.payment.domain.port.in.CreatePaymentUseCase;
import com.app.bookai.payment.domain.port.in.GetAllPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final GetAllPaymentUseCase getAllPaymentUseCase;


    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPaymentUseCase() {

        return ResponseEntity.ok(getAllPaymentUseCase.getAllPaymentUseCase());
    }

}
