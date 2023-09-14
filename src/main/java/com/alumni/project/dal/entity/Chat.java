package com.alumni.project.dal.entity;
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
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;


    private String sender;

    private String receiver;
    private String message;

    public Chat(String sender, String receiverUser) {
        this.sender = sender;
        this.receiver = receiverUser;

    }
    public Chat(){

    }

    public Chat(String sender, String receiver, ChatRoom chatRoom, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.chatRoom = chatRoom;
        this.message = message;
    }
}
