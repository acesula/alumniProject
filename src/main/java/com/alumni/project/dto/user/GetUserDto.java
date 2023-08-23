package com.alumni.project.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class GetUserDto extends UserDto {
    private UUID userId;

    GetUserDto(UUID id,String name, String surname, String email, String gender, LocalDate birthDate, String username, String role, String password, String profilePicture, String description) {
        super(id,name, surname, email, gender, birthDate, username, role, password, profilePicture, description);
    }
}




