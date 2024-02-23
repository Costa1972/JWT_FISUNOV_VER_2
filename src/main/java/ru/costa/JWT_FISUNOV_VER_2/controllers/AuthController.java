package ru.costa.JWT_FISUNOV_VER_2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.costa.JWT_FISUNOV_VER_2.dtos.JwtRequest;
import ru.costa.JWT_FISUNOV_VER_2.dtos.RegistrationUserDto;
import ru.costa.JWT_FISUNOV_VER_2.services.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        return authService.createAuthToken(request);
    }

    @PostMapping("/reg")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
