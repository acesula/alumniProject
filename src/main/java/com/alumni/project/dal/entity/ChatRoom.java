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

    private String user1;

    private String user2;

    @OneToMany(mappedBy="chatRoom", fetch = FetchType.EAGER)
    private Collection<Chat> chats;

}
