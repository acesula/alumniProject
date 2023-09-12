package com.alumni.project.service.attendees;

import com.alumni.project.dal.entity.Attendees;
import com.alumni.project.dal.entity.Event;
import com.alumni.project.dal.repository.AttendeesRepository;
import com.alumni.project.dal.repository.EventRepository;
import com.alumni.project.dto.event.AttendeesDto;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final AttendeesRepository attendeesRepository;
    private final MappingService mappingService;
    private final EventRepository eventRepository;

    @Override
    public List<AttendeesDto> allAttendees() {
        return attendeesRepository.findAll()
                .stream()
                .map(mappingService::convertToAttendeesDto)
                .toList();
    }

    @Override
    public List<AttendeesDto> findByEventId(UUID eventId) {
        return attendeesRepository.findByEvent_Id(eventId)
                .stream()
                .map(mappingService::convertToAttendeesDto)
                .toList();
    }

    @Override
    @Transactional
    public void addAttendee(UUID eventId, Attendees attendees) {
        Event newEvent = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        if(newEvent.getAttendees()
                .stream()
                .anyMatch(attendee ->
                        attendee.getAttendeeEmail().equals(attendees.getAttendeeEmail()))){
            throw new RuntimeException("Attendee already registered!");
        }else{
            attendees.setEvent(newEvent);
            newEvent.getAttendees().add(attendees);
            attendeesRepository.save(attendees);
        }
    }

    @Override
    public void deleteAttendee(UUID attendeeId) {
        attendeesRepository.deleteById(attendeeId);
    }

    @Override
    @Transactional
    public void updateAttendee(UUID attendeeId, AttendeesDto attendeesDto) {
        var attendee = attendeesRepository.findById(attendeeId).orElseThrow(RuntimeException::new);

        attendee.setAttendeeName(attendeesDto.getAttendeeName());
        attendee.setAttendeeSurname(attendeesDto.getAttendeeSurname());
        attendee.setAttendeeEmail(attendeesDto.getAttendeeEmail());

        attendeesRepository.save(attendee);
    }
}
