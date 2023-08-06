package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Request extends Base {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="friend_id")
    private User user2;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;


    public Request(User senderUser, User receiverUser, String status) {
        this.user1 = senderUser;
        this.user2= receiverUser;
        this.status = status;

    }
//    public Request(User senderUser, User receiverUser,String status) {
//        this.user1 = senderUser;
//        this.user2= receiverUser;
//        this.status = status;
//    }
}


