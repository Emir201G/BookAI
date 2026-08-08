package com.app.bookai.customer.infrastructure.persistence;

import com.app.bookai.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
}
