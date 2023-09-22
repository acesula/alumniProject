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
public class User extends Base {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String role;
    @Column(columnDefinition = "TEXT")
    private String profilePicture;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Skills> skills;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employment> employments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Interests> interests;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Announcements> announcements;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy="user1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Friends> friends;
    @OneToMany(mappedBy="friend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Friends> friend;

    @OneToMany(mappedBy="user1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ChatRoom> user1;

    @OneToMany(mappedBy="user2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ChatRoom> user2;

    @OneToMany(mappedBy="user1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Request> sender;

    @OneToMany(mappedBy="user2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Request> receiver;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<GroupChat> groupChats;


    public User(String name, String surname, String email, LocalDate birthDate, String username, String password, String gender) {
        super();
    }
}