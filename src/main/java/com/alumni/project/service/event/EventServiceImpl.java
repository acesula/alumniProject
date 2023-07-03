package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.repository.EventRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public void save(String username,Event event) {
        var user = userRepository.findByUsername(username);
        event.setUser(user);
        user.getEvents().add(event);
        eventRepository.save(event);
    }

    public ResponseEntity<ErrorResponse> saveEvent(String username,Event event) {
        try {
            if (userRepository.existsByUsername(username)) {
                save(username, event);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("User could not be found!");
                errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
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
