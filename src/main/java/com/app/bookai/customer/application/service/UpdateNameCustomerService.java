package com.app.bookai.customer.application.service;

import com.app.bookai.customer.application.dto.UpdateNameRequestDTO;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.UpdateNameCustomerUseCase;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateNameCustomerService implements UpdateNameCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer updateCustomer(UpdateNameRequestDTO requestDTO) {
        Customer customer = customerRepository.findByPhoneNumber(requestDTO.phoneNumber())
                .orElseThrow(() -> new NotFoundByPhoneNumber(requestDTO.phoneNumber()));
        customer.updateNane(requestDTO.name());
        return customerRepository.save(customer);
    }
}
