package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.NameAlreadyExistsException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.CreateBarberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.shared.exception.PhoneNumberAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBarberService implements CreateBarberUseCase {
    private final BarberRepository barberRepository;
    @Override
    public Barber createBarber(Barber barber) {
        if(barberRepository.existsByPhoneNumber(barber.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(barber.getPhoneNumber());
        }
        if (barberRepository.existsByName(barber.getName())) {
            throw new NameAlreadyExistsException(barber.getName());
        }
        return barberRepository.save(barber);
    }
}
