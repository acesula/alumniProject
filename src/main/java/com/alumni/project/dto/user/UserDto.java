package com.alumni.project.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String username;
    private String role;
    private String password;
    private String profilePicture;
    private String description;
}
