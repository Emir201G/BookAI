package com.app.bookai.customer.application.usecase;

import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.CreateCustomerUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService implements CreateCustomerUseCase {
    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }
}
