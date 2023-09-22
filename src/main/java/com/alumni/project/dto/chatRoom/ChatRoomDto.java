package com.alumni.project.dto.chatRoom;

import com.alumni.project.dto.chat.ChatDto;
import com.alumni.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {
    private UUID id;
    private UserDto user1;
    private UserDto user2;
}
