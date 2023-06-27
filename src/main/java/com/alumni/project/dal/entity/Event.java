package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Event extends Base{

    private String eventDescription;
    private LocalDate date;
    private LocalTime time;
    private String location;

    @ManyToOne
    private User user;
}
