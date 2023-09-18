package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;

import com.alumni.project.dto.chat.ChatDto;


import java.util.List;
import java.util.UUID;

public interface ChatService {
    String save(UUID sender, UUID receiver, UUID chatRoomId, String message);

    void save(UUID id, Chat chat);

    List<ChatDto> findAll();
    List<ChatDto> getAllChatsByChatRoomId(UUID chatRoomId);
    ChatDto findById(UUID id);

    void delete(UUID uuid);

}
