package ru.costa.JWT_FISUNOV_VER_2.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
