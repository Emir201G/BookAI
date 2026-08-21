package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.NameAlreadyExistsException;
import com.app.bookai.barber.domain.exception.NotFoundByName;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.UpdatePhoneNumberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.shared.exception.PhoneNumberAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePhoneNumberService implements UpdatePhoneNumberUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber updatePhoneNumber(String name, String newPhone) {
        if (barberRepository.existsByPhoneNumber(newPhone)) {
            throw new PhoneNumberAlreadyExistsException(newPhone);
        }
        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByName(name));

        barber.updatePhoneNumber(newPhone);
        return barberRepository.save(barber);
    }
}
