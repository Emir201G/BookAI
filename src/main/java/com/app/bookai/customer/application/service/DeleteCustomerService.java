package com.app.bookai.customer.application.service;

import com.app.bookai.customer.domain.port.in.DeleteCustomerUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerService implements DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;
    @Override
    public void delete(String phoneNumber) {
        customerRepository.deleteCustomerByPhoneNumber(phoneNumber);
    }
}
