package com.alumni.project.dal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class Base {
    @Id
    @GeneratedValue
    private UUID id;
}
