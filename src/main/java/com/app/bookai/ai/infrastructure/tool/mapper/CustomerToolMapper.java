package com.app.bookai.ai.infrastructure.tool.mapper;

import com.app.bookai.ai.infrastructure.tool.dto.customer.CustomerToolRequest;
import com.app.bookai.ai.infrastructure.tool.dto.customer.CustomerToolResponse;
import com.app.bookai.customer.domain.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerToolMapper {

    Customer toDomainCustomer(CustomerToolRequest customerToolRequest);
    CustomerToolResponse toDomainCustomerToolResponse(Customer customer);
    List<CustomerToolResponse> toDomainCustomerToolResponseList(List<Customer> customers);
}
