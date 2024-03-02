package ru.costa.JWT_FISUNOV_VER_2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.costa.JWT_FISUNOV_VER_2.entities.User;
import ru.costa.JWT_FISUNOV_VER_2.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/secured")
    public String securedData() {
        return "It's a secured place.";
    }

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "It's a unsecured place.";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "It's a admin's place.";
    }

    @GetMapping("/info")
    public String infoData(Principal principal) {
        return principal.getName();
    }
    @GetMapping("/all")
    public List<User> allUsers() {
        return userService.getAll();
    }
}
