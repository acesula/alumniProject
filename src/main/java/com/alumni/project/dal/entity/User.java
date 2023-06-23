package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base{
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String username;
    private String password;
    private String profilePicture;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();


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

    public User(String name, String surname, String email, LocalDate birthDate, String username, String password, String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

}