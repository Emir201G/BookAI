package com.app.bookai.customer.infrastructure.controller;

import com.app.bookai.customer.application.dto.CreateCustomerRequestDTO;
import com.app.bookai.customer.application.dto.CustomerResponseDTO;
import com.app.bookai.customer.application.dto.UpdateNameRequestDTO;
import com.app.bookai.customer.application.mapper.CustomerMapper;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.CreateCustomerUseCase;
import com.app.bookai.customer.domain.port.in.DeleteCustomerUseCase;
import com.app.bookai.customer.domain.port.in.GetAllCustomerUseCase;
import com.app.bookai.customer.domain.port.in.UpdateNameCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerMapper customerMapper;
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final UpdateNameCustomerUseCase updateNameCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {

        Customer customer = customerMapper.toDomain(requestDTO);
        Customer saved = createCustomerUseCase.createCustomer(customer);

        return ResponseEntity.ok(customerMapper.toResponse(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomer() {

        List<Customer> customer = getAllCustomerUseCase.getAllCustomers();

        return ResponseEntity.ok(customerMapper.toResponseList(customer));
    }

    @PostMapping("/update-name")
    public ResponseEntity<CustomerResponseDTO> updateName(@RequestBody UpdateNameRequestDTO requestDTO) {
        Customer customer = updateNameCustomerUseCase.updateCustomer(requestDTO);
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }
    @DeleteMapping("delete/{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        deleteCustomerUseCase.delete(phoneNumber);
        return ResponseEntity.ok().build();
    }
}
