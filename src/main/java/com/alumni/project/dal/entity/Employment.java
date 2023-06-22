package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employment extends Base{
    private String company;
    private String job;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;

    @ManyToOne
    private User user;

    public Employment(String company, String job, LocalDate startDate, LocalDate endDate, boolean status) {
        this.company = company;
        this.job = job;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
