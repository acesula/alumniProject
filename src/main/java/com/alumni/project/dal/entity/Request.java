package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request extends Base {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="friend_id")
    private User user2;



    public Request(User senderUser, User receiverUser) {
        this.user1 = senderUser;
        this.user2= receiverUser;

    }

}


