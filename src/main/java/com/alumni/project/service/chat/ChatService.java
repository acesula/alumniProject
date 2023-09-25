package com.alumni.project.service.chat;

import com.alumni.project.dto.chat.ChatDto;


import java.util.List;
import java.util.UUID;

public interface ChatService {
    void saveByChatRoomId(UUID chatRoomId, String message);

    void saveByGroupChatId(UUID groupChatId, String message);
    List<ChatDto> findAll();
    List<ChatDto> getAllChatsByChatRoomId(UUID chatRoomId);

    List<ChatDto> getAllChatsByGroupChatId(UUID groupChatId);
    ChatDto findById(UUID id);

    void delete(UUID uuid);

}
