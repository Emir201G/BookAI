package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;

import java.util.List;

public interface GetBarberByPhoneNumberUseCase {
    Barber getBarberByPhoneNumber(String phoneNumber);
}
