package com.alumni.project.controller.event;

import com.alumni.project.dal.entity.Attendees;
import com.alumni.project.dto.event.AttendeesDto;
import com.alumni.project.service.attendees.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {


    private final AttendeeService attendeeService;
    @GetMapping
    public List<AttendeesDto> getAllAttendeesPerEvent() {
        return attendeeService.allAttendees();
    }

    @GetMapping("/{id}")
    public List<AttendeesDto> getAttendeeByEventId(@PathVariable UUID id) {
        return attendeeService.findByEventId(id);
    }

    @PostMapping("/{id}")
    public void addAttendee(@PathVariable UUID id, @RequestBody Attendees attendees) {
        attendeeService.addAttendee(id, attendees);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendee(@PathVariable UUID id) {
        attendeeService.deleteAttendee(id);
    }

    @PatchMapping("/{id}")
    public void updateAttendee(@PathVariable UUID id, @RequestBody AttendeesDto attendeesDto) {
        attendeeService.updateAttendee(id, attendeesDto);
    }
}
