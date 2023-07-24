package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {

    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String username;
    private String password;
    private String description;

    private String role;
    private String profilePicture;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Skills> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Employment> employments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ContactDetails contactDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Interests> interests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Announcements> announcements;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userConnection", fetch = FetchType.LAZY)
    private List<UserConnection> userConnections;


    @OneToMany(mappedBy="user1", fetch = FetchType.EAGER)
    private Collection<Friends> friends;
    @OneToMany(mappedBy="friend", fetch = FetchType.EAGER)
    private Collection<Friends> friend;

    public User(String name, String surname, String email, LocalDate birthDate, String username, String password, String gender) {
        super();
    }
}