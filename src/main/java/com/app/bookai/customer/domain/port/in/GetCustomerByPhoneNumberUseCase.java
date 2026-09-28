package com.app.bookai.customer.domain.port.in;

import com.app.bookai.customer.domain.model.Customer;

public interface GetCustomerByPhoneNumberUseCase {
    Customer getCustomerByName(String phoneNumber);
}
