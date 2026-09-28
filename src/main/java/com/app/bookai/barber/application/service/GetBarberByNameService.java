package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.GetBarberByNameUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBarberByNameService implements GetBarberByNameUseCase {

    private final BarberRepository barberRepository;

    @Override
    public Barber getBarberByName(String name) {
        return barberRepository.findByName(name)
                .orElseThrow(() ->
                        new NotFoundByNameException(name));
    }


}
