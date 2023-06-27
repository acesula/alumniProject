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
@NoArgsConstructor
@AllArgsConstructor
public class Interests extends Base{
    private String interestDescription;

    @ManyToOne
    private User user;

    public Interests(String interestDescription) {
        this.interestDescription = interestDescription;
    }
}
