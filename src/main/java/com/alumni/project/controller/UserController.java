package com.alumni.project.controller;

import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public GetUserDto save(@RequestBody UserDto dto) {
        return userService.save(dto);
    }

    @GetMapping
    public List<GetUserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public GetUserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PatchMapping("/{id}")
    public GetUserDto update(@PathVariable UUID id, @RequestBody UserDto dto) {
        return userService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
