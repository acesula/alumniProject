package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Interests extends Base{
    private String interestDescription;

    @ManyToOne
    private User user;
}
