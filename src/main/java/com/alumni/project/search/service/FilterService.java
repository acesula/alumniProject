package com.alumni.project.search.service;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.search.dto.SearchRequestDto;

import java.util.List;

public interface FilterService {

    public List<UserDto> getUsers(SearchRequestDto searchRequestDto);
}
