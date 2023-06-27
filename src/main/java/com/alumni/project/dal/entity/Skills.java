package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@AllArgsConstructor
public class Skills extends Base {
    private String skillField;
    private String skillDescription;

    @ManyToOne
    private User user;

    public Skills(String skillField, String skillDescription) {
        this.skillField = skillField;
        this.skillDescription = skillDescription;
    }

    public Skills() {

    }
}
