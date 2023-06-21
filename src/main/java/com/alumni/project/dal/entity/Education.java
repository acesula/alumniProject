package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Education extends Base {
    private String school;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean finished;

    @ManyToOne
    private User user;
}
