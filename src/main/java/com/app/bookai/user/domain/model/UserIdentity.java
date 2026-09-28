package com.app.bookai.user.domain.model;

import com.app.bookai.shared.enums.RoleType;

public record UserIdentity (
    Long id,
    String phoneNumber,
    RoleType role){
}
