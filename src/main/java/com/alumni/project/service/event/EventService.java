package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EventService {

    void save(String username, Event event);

    ResponseEntity<ErrorResponse> saveEvent(String username, Event event);

    List<EventDto> findAll();

    List<EventDto> findByUser(String username);

    void delete(UUID uuid);

    EventDto update(UUID uuid, EventDto eventDto);
}
