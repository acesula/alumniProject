package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;
import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.entity.UserConnection;
import com.alumni.project.dto.event.EventDto;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    void save(UUID id, Chat chat);

    List<Chat> findAll();
    Chat findById(UUID id);

    List<Chat> findByUser(UUID id);

    void delete(UUID uuid);

    Chat update(UUID uuid, Chat chat);
}
