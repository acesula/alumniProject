package com.alumni.project.service.chatRoom;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.user.GetChatRoomDto;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    String save(UUID id1, UUID id2);

    List<ChatRoomDto> findAll();
    ChatRoomDto findById(UUID id);
    ChatRoom findChatRoomById(UUID id);


    void delete(UUID id);
    List<GetChatRoomDto> findAllChatRoomsById(UUID id);


}
