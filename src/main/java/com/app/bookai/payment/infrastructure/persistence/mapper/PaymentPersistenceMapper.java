package com.app.bookai.payment.infrastructure.persistence.mapper;

import com.app.bookai.payment.domain.model.Payment;
import com.app.bookai.payment.infrastructure.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {

    Payment toDomain(PaymentEntity paymentEntity);

    PaymentEntity toEntity(Payment payment);

    List<Payment> toDomainList(List<PaymentEntity> paymentEntityList);
}
