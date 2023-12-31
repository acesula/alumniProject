package com.alumni.project.dto.request;

import java.util.UUID;

public interface UserRequestDto {
    UUID getId();

    String getName();

    String getSurname();
    String getUsername();
    String getImage();
    UUID getMainId();
}
