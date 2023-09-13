package com.alumni.project.dto.skills;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SkillsDto {
    private UUID id;
    private String skillField;
    private String skillDescription;
}
