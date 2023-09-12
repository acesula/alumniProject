package com.alumni.project.dal.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends Base{

    private String user1;

    private String user2;

    @OneToMany(mappedBy="chatRoom", fetch = FetchType.EAGER)
    private Collection<Chat> chats;


    public ChatRoom(String sender, String receiver) {
        this.user1 = sender;
        this.user2 = receiver;
    }
}
