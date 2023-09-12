package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;
<<<<<<< HEAD
import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.entity.UserConnection;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.event.EventDto;
=======
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368

import java.util.List;
import java.util.UUID;

public interface ChatService {
    String save(String sender, String receiver, UUID chatRoomId, String message);

    List<ChatDto> findAll();
    ChatDto findById(UUID id);

//    List<Chat> findByUser(UUID id);
//
    void delete(UUID uuid);

}
