package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EventService {

    void save(Event event);

    EventDto findById(UUID uuid);
    ResponseEntity<ErrorResponse> saveEvent(Event event);

    List<EventDto> findAll();

    List<EventDto> findByUser();

    void delete(UUID uuid);

    EventDto update(UUID uuid, EventDto eventDto);
}
