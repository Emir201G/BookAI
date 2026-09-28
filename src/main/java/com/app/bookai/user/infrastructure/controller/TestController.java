package com.app.bookai.user.infrastructure.controller;

import com.app.bookai.user.domain.model.UserIdentity;
import com.app.bookai.user.domain.port.in.IdentifyUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class TestController {

    private final IdentifyUserUseCase identifyUserUseCase;

    @GetMapping("/identify")
    public UserIdentity prueba(@RequestParam String phoneNumber) {

       return identifyUserUseCase.identify(phoneNumber);

    }
}