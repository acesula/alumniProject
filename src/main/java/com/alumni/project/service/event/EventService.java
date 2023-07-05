package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.event.EventDto;

import java.util.List;
import java.util.UUID;

public interface EventService {

    void save(String username, Event event);

    List<EventDto> findAll();

    List<EventDto> findByUser(String username);

    void delete(UUID uuid);

    Event update(UUID uuid, Event event);
}
