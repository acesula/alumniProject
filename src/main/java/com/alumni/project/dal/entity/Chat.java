package com.alumni.project.dal.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat extends Base {


    private String sender;
    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    private GroupChat groupChat;
    public Chat(ChatRoom chatRoom, String message) {
        this.chatRoom = chatRoom;
        this.message = message;
    }
}
