package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;

public interface GetBarberByNameUseCase {
    Barber getBarberByName(String name);
}
