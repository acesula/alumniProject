package com.alumni.project.service.attendees;

import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.repository.AttendeesRepository;
import com.alumni.project.dal.repository.EventRepository;
import com.alumni.project.dto.event.AttendeesDto;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final AttendeesRepository attendeesRepository;
    private final MappingService mappingService;
    private final EventRepository eventRepository;

    @Override
    public List<AttendeesDto> allAttendees(UUID eventId) {
        return attendeesRepository.findByEvent_Id(eventId)
                .stream()
                .map(mappingService::convertToAttendeesDto)
                .toList();
    }

    @Override
    public void addAttendee(UUID eventId, AttendeesDto attendeesDto) {
        Event newEvent = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        newEvent.getAttendees().add(mappingService.convertToAttendees(attendeesDto));
        attendeesRepository.save(mappingService.convertToAttendees(attendeesDto));

    }

    @Override
    public void deleteAttendee(UUID attendeeId) {
        attendeesRepository.deleteById(attendeeId);
    }
}
