package com.alumni.project.dto.user;

import java.util.UUID;

public interface GetChatRoomDto {
    UUID getId();
    String getUsername();
    UUID getMainId();
    String getImage();
}
