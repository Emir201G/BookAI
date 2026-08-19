package com.app.bookai.customer.application.mapper;

import com.app.bookai.customer.application.dto.CreateCustomerRequestDTO;
import com.app.bookai.customer.application.dto.CustomerResponseDTO;
import com.app.bookai.customer.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "role", expression = "java(com.app.bookai.shared.enums.RoleType.CUSTOMER)")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    Customer toDomain(CreateCustomerRequestDTO requestDTO);

    CustomerResponseDTO toResponse(Customer customer);

    List<CustomerResponseDTO> toResponseList(List<Customer> customers);
}
