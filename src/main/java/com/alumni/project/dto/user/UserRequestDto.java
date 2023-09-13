package com.alumni.project.dto.user;

import java.util.UUID;

public interface UserRequestDto {
    UUID getId();
    String getUsername();
    String getImage();
    UUID getMainId();
}
