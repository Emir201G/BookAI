package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.GetBarberByPhoneNumberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBarberByPhoneNumberService implements GetBarberByPhoneNumberUseCase {
    private final BarberRepository barberRepository;


    @Override
    public Barber getBarberByPhoneNumber(String phoneNumber) {
        Barber barber = barberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundByPhoneNumber(phoneNumber));
        return barber;
    }
}
