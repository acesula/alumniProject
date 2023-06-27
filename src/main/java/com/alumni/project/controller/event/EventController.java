package com.alumni.project.controller.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.service.event.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping
    public Event save(@RequestBody Event event) {
        return eventService.save(event);
    }

    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
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
