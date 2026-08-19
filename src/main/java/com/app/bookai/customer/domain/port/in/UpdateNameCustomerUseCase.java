package com.app.bookai.customer.domain.port.in;

import com.app.bookai.customer.application.dto.UpdateNameRequestDTO;
import com.app.bookai.customer.domain.model.Customer;


public interface UpdateNameCustomerUseCase {
    Customer updateCustomer(UpdateNameRequestDTO requestDTO);
}
