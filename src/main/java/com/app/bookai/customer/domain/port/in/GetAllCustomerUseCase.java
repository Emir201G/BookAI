package com.app.bookai.customer.domain.port.in;

import com.app.bookai.customer.domain.model.Customer;

import java.util.List;

public interface GetAllCustomerUseCase {
    List<Customer> getAllCustomers();
}
