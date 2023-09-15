package com.alumni.project.dto.event;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
public class EventDto {
    private UUID id;
    private String eventDescription;
    private String eventTitle;
    private LocalDate startDate;
    private String startTime;
    private String endTime;
    private LocalDate endDate;
    private String location;
    private UUID userId;
}
