package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Base {
    @Id
    private Long id;
}
