package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skills extends Base {
    private String skillField;
    private String skillDescription;

    @ManyToOne
    private User user;
}
