package com.alumni.project.dto.employment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmploymentDto {
    private String company;
    private String job;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
}
