package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GroupChat extends Base{

    private String admin;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="groupChat", cascade = CascadeType.ALL)
    private List<Chat> chats;
}
