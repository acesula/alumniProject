package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class User extends Base {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;
}
