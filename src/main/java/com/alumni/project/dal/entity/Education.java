package com.alumni.project.dal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Education extends Base {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    @JsonFormat(pattern = "yyyy-MM-dd" , shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd" , shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;
    private boolean finished;


    @ManyToOne
    private User user;

    public Education(String institution, String degree, String fieldOfStudy, LocalDate startDate, LocalDate endDate, boolean finished) {
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate= endDate;
        this.finished = finished;

    }

}
