package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {

    void save(String username, Event event);

    List<Event> findAll();

    void delete(UUID uuid);

    Event update(UUID uuid, Event event);
}
