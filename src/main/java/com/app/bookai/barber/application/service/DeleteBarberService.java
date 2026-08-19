package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.DeleteBarberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBarberService implements DeleteBarberUseCase {
    private final BarberRepository barberRepository;
    @Override
    public void delete(Barber barber) {
       barberRepository.remove(barber);
    }
}
