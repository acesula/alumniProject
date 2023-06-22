package com.alumni.project.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String username;
    private String password;
}
