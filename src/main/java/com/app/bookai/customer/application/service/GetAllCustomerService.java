package com.app.bookai.customer.application.service;

import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.GetAllCustomerUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllCustomerService implements GetAllCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
