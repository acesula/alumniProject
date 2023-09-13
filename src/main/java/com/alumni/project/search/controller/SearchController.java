package com.alumni.project.search.controller;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.search.dto.SearchRequestDto;
import com.alumni.project.search.service.FilterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final FilterServiceImpl filterService;

    @PostMapping
    public List<UserDto> getUsers(@RequestBody SearchRequestDto searchRequestDto){
        return filterService.getUsers(searchRequestDto);
    }
}
