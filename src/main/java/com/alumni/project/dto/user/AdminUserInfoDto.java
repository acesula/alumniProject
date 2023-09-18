package com.alumni.project.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AdminUserInfoDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String password;
    private String username;
    private String role;
    private String profilePicture;
    private String description;
    public boolean enabled;
}
