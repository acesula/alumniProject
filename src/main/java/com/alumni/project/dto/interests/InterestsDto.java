package com.alumni.project.dto.interests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InterestsDto {
    private UUID id;
    private String interestDescription;
}
