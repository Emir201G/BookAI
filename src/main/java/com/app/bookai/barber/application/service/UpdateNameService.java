package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.NotFoundByName;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.in.UpdateNameUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNameService implements UpdateNameUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber updateName(String name, String newName) {

        if(newName.isEmpty()){
            throw new RuntimeException("newName cannot be empty");
        }

        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByName(name));

        barber.updateName(newName);
        return barberRepository.save(barber);
    }
}
