package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Attendees extends Base{

    private String attendeeName;
    private String attendeeSurname;
    private String attendeeEmail;

    @ManyToOne
    private Event event;
}
