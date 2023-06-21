package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employment extends Base{
    private String employer;

    @ManyToOne
    private User user;
}
