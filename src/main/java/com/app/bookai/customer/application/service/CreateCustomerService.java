package com.app.bookai.customer.application.service;

import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.CreateCustomerUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.shared.enums.RoleType;
import com.app.bookai.shared.exception.PhoneNumberAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(customer.getPhoneNumber());
        }

        customer.setRole(RoleType.CUSTOMER);
        customer.setCreatedAt(LocalDateTime.now());

        return customerRepository.save(customer);
    }

}
