package com.alumni.project.dto.event;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttendeesDto {
    private UUID id;
    private String attendeeName;
    private String attendeeSurname;
    private String attendeeEmail;
    private UUID eventId;
}
