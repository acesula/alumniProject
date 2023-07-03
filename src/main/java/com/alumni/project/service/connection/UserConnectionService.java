package com.alumni.project.service.connection;

import com.alumni.project.dal.entity.UserConnection;
import com.alumni.project.dto.connection.UserConnectionDto;

import java.util.List;
import java.util.UUID;

public interface UserConnectionService {

    void save(String username, UserConnection userConnection);

    List<UserConnectionDto> findAll();

    UserConnection findById(UUID uuid);

    void delete(String username);
}
