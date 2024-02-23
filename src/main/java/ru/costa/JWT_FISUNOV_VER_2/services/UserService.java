package ru.costa.JWT_FISUNOV_VER_2.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.costa.JWT_FISUNOV_VER_2.entities.User;
import ru.costa.JWT_FISUNOV_VER_2.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public Optional<User> findUserInDBByName(String username) {
        return userRepository.findUserByUsername(username);
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserInDBByName(username).orElseThrow(() -> new UsernameNotFoundException(
              String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user) {
        if (roleService.findRoleByName("ROLE_USER").isPresent()) {
            user.setRoles(List.of(roleService.findRoleByName("ROLE_USER").get()));
        }
    }
}
