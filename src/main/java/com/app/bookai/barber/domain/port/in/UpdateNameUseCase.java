package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;

public interface UpdateNameUseCase {
    Barber updateName(String name,String newName);
}
