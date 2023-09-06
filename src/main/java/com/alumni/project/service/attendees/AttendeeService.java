package com.alumni.project.service.attendees;

import com.alumni.project.dto.event.AttendeesDto;

import java.util.List;
import java.util.UUID;

public interface AttendeeService {

    List<AttendeesDto> allAttendees(UUID eventId);

    void addAttendee(UUID eventId, AttendeesDto attendeesDto);

    void deleteAttendee(UUID attendeeId);
}
