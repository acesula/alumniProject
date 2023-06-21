package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Employment extends Base{
    private String company;
    private String job;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;

    @ManyToOne
    private User user;
}
