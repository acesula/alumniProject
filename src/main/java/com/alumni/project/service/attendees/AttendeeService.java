package com.alumni.project.service.attendees;

import com.alumni.project.dal.entity.Attendees;
import com.alumni.project.dto.event.AttendeesDto;

import java.util.List;
import java.util.UUID;

public interface AttendeeService {

    List<AttendeesDto> allAttendees();

    List<AttendeesDto> findByEventId(UUID eventId);

    void addAttendee(UUID eventId, Attendees attendees);

    void deleteAttendee(UUID attendeeId);

    void updateAttendee(UUID attendeeId, AttendeesDto attendeesDto);
}
