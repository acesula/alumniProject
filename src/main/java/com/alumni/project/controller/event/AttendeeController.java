package com.alumni.project.controller.event;

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
    @GetMapping("/{id}")
    public List<AttendeesDto> getAllAttendees(@PathVariable UUID id) {
        return attendeeService.allAttendees(id);
    }

    @PostMapping("/{id}")
    public void addAttendee(@PathVariable UUID id, @RequestBody AttendeesDto attendeesDto) {
        attendeeService.addAttendee(id, attendeesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendee(@PathVariable UUID id) {
        attendeeService.deleteAttendee(id);
    }
}
