package com.app.bookai.customer.domain.port.out;

import com.app.bookai.customer.domain.model.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();
   Customer findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    void deleteCustomerByPhoneNumber(String phoneNumber);
}
