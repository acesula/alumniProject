package com.alumni.project.service.user;

import com.alumni.project.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService{

    void save(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(UUID id);

    UserDto update(UUID id, UserDto user);

    void delete(String username);


}
