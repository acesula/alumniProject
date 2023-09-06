package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Event extends Base{

    private String eventDescription;
    private LocalDate startDate;
    private String startTime;
    private String endTime;
    private LocalDate endDate;
    private String location;

    @OneToMany(mappedBy = "event")
    private List<Attendees> attendees;


    @ManyToOne
    private User user;
}
