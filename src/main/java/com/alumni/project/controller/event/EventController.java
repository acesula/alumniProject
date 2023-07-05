package com.alumni.project.controller.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.event.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Event event) {
        return eventService.saveEvent(username, event);
    }

    @GetMapping
    public List<EventDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{username}")
    public List<EventDto> findByUser(@PathVariable String username) {
        return eventService.findByUser(username);
    }

    @PatchMapping("/{id}")
    public Event update(@PathVariable UUID id, @RequestBody Event event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }
}
