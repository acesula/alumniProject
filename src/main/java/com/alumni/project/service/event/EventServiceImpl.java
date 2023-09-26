package com.alumni.project.service.event;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.repository.EventRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void save(Event event) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        event.setUser(user);
        user.getEvents().add(event);
        eventRepository.save(event);
    }

    @Override
    public EventDto findById(UUID uuid) {
        var optional = eventRepository.findById(uuid);
        if (optional.isPresent()) {
            return mappingService.convertToEventDto(optional.get());
        }
            throw new RuntimeException("Event not found!");

    }

    public ResponseEntity<ErrorResponse> saveEvent(Event event) {
        try {
            if (userRepository.existsById(authenticatedUser().getId())) {
                save(event);
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
    public List<EventDto> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(mappingService::convertToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findByUser() {
        return eventRepository.findByUser_Id(authenticatedUser().getId())
                .stream()
                .map(mappingService::convertToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        eventRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public EventDto update(UUID uuid, EventDto event) {
        var e = eventRepository.findById(uuid).orElseThrow(RuntimeException::new);
        e.setEventTitle(event.getEventTitle());
        e.setEventDescription(event.getEventDescription());
        e.setStartDate(event.getStartDate());
        e.setStartTime(event.getStartTime());
        e.setEndTime(event.getEndTime());
        e.setEndDate(event.getEndDate());
        e.setLocation(event.getLocation());

        return mappingService.convertToEventDto(eventRepository.save(e));
    }
}
