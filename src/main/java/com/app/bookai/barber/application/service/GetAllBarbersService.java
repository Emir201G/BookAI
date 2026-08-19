package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.GetAllBarbersUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.GetAllCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllBarbersService implements GetAllBarbersUseCase {
    private final BarberRepository barberRepository;

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.getAll();
    }
}
