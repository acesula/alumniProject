package com.alumni.project.controller.user;

import com.alumni.project.dto.user.*;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ErrorResponse> register(@Valid @RequestBody UserDto userDto) {
       return userService.register(userDto);
    }



    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping("/user-info/{username}")
    public List<UserInfoDto> findUserInfoByUsername(@PathVariable String username) {
        return userService.getUserInfoByUsername(username);
    }

    @PatchMapping("/uploadImage/{id}")
    public void updateProfilePicture(@RequestParam("profilePicture") MultipartFile multipartFile, @PathVariable UUID id) throws IOException {
        userService.uploadProfilePicture(multipartFile, id);
    }

    @PatchMapping("/updateBio/{id}")
    public void updateBio(@PathVariable UUID id, @RequestParam("bio") String bio)  {
        userService.updateBio(id, bio);
    }

    @PatchMapping("/updateUsername/{id}")
    public void updateUsername(@PathVariable UUID id, @RequestParam("username") String username)  {
        userService.updateUsername(id, username);
    }

    @PatchMapping("/updateEmail/{id}")
    public void updateEmail(@PathVariable UUID id, @RequestParam("email") String email)  {
        userService.updateEmail(id, email);
    }

    @PatchMapping("/updatePassword/{id}")
    public void updatePassword(@PathVariable UUID id,@RequestBody ChangePasswordDto password) {
        userService.updatePassword(id, password);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable UUID id, @RequestBody UserDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{username}")
    @Transactional
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }

    @GetMapping("/users/{username}")
    public List<UserInfoDto> getUserInfoByUsername(@PathVariable String username) {
        return userService.getUserInfoByUsername(username);
    }


}
