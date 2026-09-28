package com.app.bookai.user.domain.port.in;

import com.app.bookai.user.domain.model.UserIdentity;

public interface IdentifyUserUseCase {
    UserIdentity identify(String phoneNumber);
}
