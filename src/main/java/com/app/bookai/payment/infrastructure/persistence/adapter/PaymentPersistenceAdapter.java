package com.app.bookai.payment.infrastructure.persistence.adapter;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.payment.domain.model.Payment;
import com.app.bookai.payment.domain.port.out.PaymentRepository;
import com.app.bookai.payment.infrastructure.persistence.entity.PaymentEntity;
import com.app.bookai.payment.infrastructure.persistence.mapper.PaymentPersistenceMapper;
import com.app.bookai.payment.infrastructure.persistence.repository.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    @Transactional
    public Payment save(Payment payment) {

        PaymentEntity paymentEntity = paymentPersistenceMapper.toEntity(payment);

        jpaPaymentRepository.save(paymentEntity);

        return paymentPersistenceMapper.toDomain(paymentEntity);
    }

    @Override
    public List<Payment> getAllPayment() {
        List<PaymentEntity> paymentEntityList = jpaPaymentRepository.findAll();
        return paymentPersistenceMapper.toDomainList(paymentEntityList);
    }
}
