package com.app.bookai.customer.application.service;

import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.GetCustomerByPhoneNumberUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerByPhoneNumberService implements
        GetCustomerByPhoneNumberUseCase {

    private final CustomerRepository customerRepository;
    @Override
    public Customer getCustomerByName(String phoneNumber) {

        return customerRepository
                .findByPhoneNumber(phoneNumber);
    }
}
