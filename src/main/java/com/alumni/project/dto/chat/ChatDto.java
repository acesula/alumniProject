package com.alumni.project.dto.chat;

import com.alumni.project.dal.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private ChatRoom chatRoom;


    private String sender;

    private String receiver;
}
