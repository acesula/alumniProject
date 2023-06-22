package com.alumni.project.service.user;

import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    GetUserDto save(UserDto userDto);

    List<GetUserDto> findAll();

    GetUserDto findById(UUID id);

    GetUserDto update(UUID id, UserDto user);

    void delete(UUID id);
}
