package com.alumni.project.dto.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class EventDto {
    private String eventDescription;
    private LocalDate date;
    private LocalTime time;
    private String location;
}
