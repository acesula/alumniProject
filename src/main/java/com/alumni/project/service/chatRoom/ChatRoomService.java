package com.alumni.project.service.chatRoom;

import com.alumni.project.dal.entity.Chat;
import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dto.chatRoom.ChatRoomDto;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    String save(UUID id1, UUID id2);

    List<ChatRoomDto> findAll();
    ChatRoomDto findById(UUID id);

//    List<ChatRoom> findByUser(UUID id);

    void delete(UUID id);


}
