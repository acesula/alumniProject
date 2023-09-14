package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Education extends Base {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String startYear;
    private String endYear;
    private boolean finished;


    @ManyToOne
    private User user;

    public Education(String institution, String degree, String fieldOfStudy, String startYear, String endYear, boolean finished) {
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startYear = startYear;
        this.endYear = endYear;
        this.finished = finished;

    }

}
