package ru.costa.JWT_FISUNOV_VER_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.costa.JWT_FISUNOV_VER_2.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
