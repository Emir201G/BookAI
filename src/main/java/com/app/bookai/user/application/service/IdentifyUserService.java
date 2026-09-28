package com.app.bookai.user.application.service;

import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.shared.enums.RoleType;
import com.app.bookai.user.domain.model.UserIdentity;
import com.app.bookai.user.domain.port.in.IdentifyUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IdentifyUserService implements IdentifyUserUseCase {

    private final BarberRepository barberRepository;
    private final CustomerRepository customerRepository;

    @Value("${bookai.owner.phone-number}")
    private String ownerPhoneNumber;

    @Override
    public UserIdentity identify(String phoneNumber) {

        if (ownerPhoneNumber.equals(phoneNumber)) {
            return new UserIdentity(
                    null,
                    phoneNumber,
                    RoleType.OWNER
            );
        }

        return barberRepository
                .findByPhoneNumber(phoneNumber)
                .map(barber -> new UserIdentity(
                        barber.getId(),
                        barber.getPhoneNumber(),
                        RoleType.WORKER
                ))
                .orElseGet(() -> {


                    Customer customer =
                            customerRepository.findByPhoneNumber(phoneNumber);

                    if (customer != null) {
                        return new UserIdentity(
                                customer.getId(),
                                customer.getPhoneNumber(),
                                RoleType.CUSTOMER
                        );
                    }

                    Customer newCustomer = new Customer(
                            null,
                            null,
                            phoneNumber,
                            RoleType.CUSTOMER,
                            LocalDateTime.now(),
                            null
                    );

                    Customer savedCustomer =
                            customerRepository.save(newCustomer);

                    return new UserIdentity(
                            savedCustomer.getId(),
                            savedCustomer.getPhoneNumber(),
                            RoleType.CUSTOMER
                    );
                });
    }
}