package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users_table")
public class User extends Base {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Education> educations;

    @OneToMany(mappedBy = "user")
    private List<Skills> skills;

    @OneToMany(mappedBy = "user")
    private List<Employment> employments;

    @OneToOne
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "user")
    private List<Interests> interests;
}
