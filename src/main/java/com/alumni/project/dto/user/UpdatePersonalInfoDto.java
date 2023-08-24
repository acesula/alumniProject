package com.alumni.project.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePersonalInfoDto {

    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;
}
