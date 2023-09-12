package com.alumni.project.dto.user;

import java.time.LocalDate;
import java.util.UUID;

public interface UserRequestDto {
    UUID getId();
    String getUsername();
    String getUser1();
    String getUser2();
     String getStatus();
     LocalDate getStartDate();
     LocalDate getEndDate();
    String getImage();
    UUID getMainId();
}
