package com.alumni.project.service.chatRoom;

import com.alumni.project.dto.chatRoom.ChatRoomDto;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    String save(String sender, String receiver);

    List<ChatRoomDto> findAll();
    ChatRoomDto findById(UUID id);

    void delete(UUID id);


}
