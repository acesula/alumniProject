package com.alumni.project.dal.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

public class Chat extends Base {
    @ManyToOne
    @JoinColumn(name="sender_user_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name="sender_user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="recever_user_id")
    private User user2;
}
