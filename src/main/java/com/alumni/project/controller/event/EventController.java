package com.alumni.project.controller.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    @PostMapping
    public ResponseEntity<ErrorResponse> save(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/{id}")
    public EventDto findById(@PathVariable UUID id) {
        return eventService.findById(id);
    }

    @GetMapping
    public List<EventDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/eventsByUser")
    public List<EventDto> findByUser() {
        return eventService.findByUser();
    }

    @PatchMapping("/{id}")
    public EventDto update(@PathVariable UUID id, @RequestBody EventDto eventDto) {
        return eventService.update(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }
}
