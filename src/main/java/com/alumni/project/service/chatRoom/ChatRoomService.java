package com.alumni.project.service.chatRoom;

import com.alumni.project.dal.entity.Chat;
import com.alumni.project.dal.entity.ChatRoom;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    void save(String username, ChatRoom chatRoom);

    List<ChatRoom> findAll();
    ChatRoom findById(UUID id);

    List<ChatRoom> findByUser(UUID id);

    void delete(UUID id);

    ChatRoom update(UUID uuid, ChatRoom chatRoom);
}
