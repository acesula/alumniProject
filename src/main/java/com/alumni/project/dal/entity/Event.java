package com.alumni.project.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Event extends Base{

    @Column(columnDefinition = "TEXT")
    private String eventDescription;
    private String eventTitle;
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
