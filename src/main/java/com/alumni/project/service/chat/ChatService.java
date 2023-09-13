package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.event.EventDto;


import java.util.List;
import java.util.UUID;

public interface ChatService {
    String save(String sender, String receiver, UUID chatRoomId, String message);

    void save(UUID id, Chat chat);

    List<ChatDto> findAll();
    ChatDto findById(UUID id);

//    List<Chat> findByUser(UUID id);
//
    void delete(UUID uuid);

}
