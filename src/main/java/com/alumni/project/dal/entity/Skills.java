package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Skills extends Base {
    private String skillField;
    private String skillDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
