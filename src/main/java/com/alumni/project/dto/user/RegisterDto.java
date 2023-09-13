package com.alumni.project.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {

    private String name;
    private String surname;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String username;
    private String password;
    private String role;
}
