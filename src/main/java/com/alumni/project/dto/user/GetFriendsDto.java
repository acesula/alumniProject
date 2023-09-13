package com.alumni.project.dto.user;

import com.alumni.project.dal.entity.User;

import java.util.UUID;

public interface GetFriendsDto {
    UUID getId();
    String getUsername();
    String getImage();
    UUID getMainId();
}
