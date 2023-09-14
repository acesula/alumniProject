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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="friend_id")
    private User user2;


    @OneToMany(mappedBy="chatRoom", fetch = FetchType.EAGER)
    private Collection<Chat> chats;


    public ChatRoom(User user1, User user2) {
        this.user1 = user1;
        this.user2= user2;
    }
}
