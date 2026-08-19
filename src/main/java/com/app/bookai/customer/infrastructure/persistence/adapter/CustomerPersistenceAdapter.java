package com.app.bookai.customer.infrastructure.persistence.adapter;

import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.customer.infrastructure.persistence.entity.CustomerEntity;
import com.app.bookai.customer.infrastructure.persistence.mapper.CustomerPersistenceMapper;
import com.app.bookai.customer.infrastructure.persistence.repository.JpaCustomerRepository;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;


    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerPersistenceMapper.toEntity(customer);
        CustomerEntity savedEntity = jpaCustomerRepository.save(customerEntity); // Guardar la referencia actualizada
        return customerPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntities = jpaCustomerRepository.findAll();
        List<Customer> customers = customerPersistenceMapper.toDomain(customerEntities);
        return customers;
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        CustomerEntity customerEntity = jpaCustomerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundByPhoneNumber(phoneNumber));
        Customer customer = customerPersistenceMapper.toDomain(customerEntity);
        return Optional.of(customer);
    }


    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return jpaCustomerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteCustomerByPhoneNumber(String phoneNumber) {
        CustomerEntity customerEntity = jpaCustomerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundByPhoneNumber(phoneNumber));
        jpaCustomerRepository.delete(customerEntity);
    }
}
