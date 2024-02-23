package ru.costa.JWT_FISUNOV_VER_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.costa.JWT_FISUNOV_VER_2.entities.Role;
import ru.costa.JWT_FISUNOV_VER_2.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> getUserRole() {
        return roleRepository.findByName("ROLE_USER");
    }
}
