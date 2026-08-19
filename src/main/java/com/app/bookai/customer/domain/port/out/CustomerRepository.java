package com.app.bookai.customer.domain.port.out;

import com.app.bookai.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    void deleteCustomerByPhoneNumber(String phoneNumber);
}
