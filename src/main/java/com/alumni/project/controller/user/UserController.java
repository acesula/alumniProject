package com.alumni.project.controller.user;

import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserLoginDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.user.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<ErrorResponse> register(@Valid @RequestBody UserDto userDto) {
       return userService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ErrorResponse> login(@Valid @RequestBody UserLoginDto login) {
        return userService.login(login);
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
        return userService.update(id, dto);
    }

    @DeleteMapping("/{username}")
    @Transactional
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }


}
