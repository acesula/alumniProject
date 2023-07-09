package com.alumni.project.dal.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter

public class ChatRoom extends Base{
    @ManyToOne
    @JoinColumn(name="sender_user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="recever_user_id")
    private User user2;

    @OneToMany(mappedBy="Chat", fetch = FetchType.EAGER)
    private Collection<Chat> chats;

}
