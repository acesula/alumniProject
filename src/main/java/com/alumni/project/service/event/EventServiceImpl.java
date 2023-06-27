package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(UUID uuid) {
        eventRepository.deleteById(uuid);
    }

    @Override
    public Event update(UUID uuid, Event event) {
        var e = eventRepository.findById(uuid).orElseThrow(RuntimeException::new);
        e.setEventDescription(event.getEventDescription());
        e.setDate(event.getDate());
        e.setTime(event.getTime());
        e.setLocation(event.getLocation());

        return eventRepository.save(e);
    }
}
