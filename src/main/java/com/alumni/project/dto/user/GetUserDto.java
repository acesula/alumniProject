package com.alumni.project.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetUserDto extends UserDto {
    private UUID userId;
}
