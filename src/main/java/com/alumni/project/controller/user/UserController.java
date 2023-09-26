package com.alumni.project.controller.user;

import com.alumni.project.dto.user.*;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping
    public ResponseEntity<ErrorResponse> register(@Valid @RequestBody RegisterDto registerDto) {
       return userService.register(registerDto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }


    @PatchMapping("/uploadImage")
    public void updateProfilePicture(@RequestParam("profilePicture") MultipartFile multipartFile) throws IOException {
        userService.uploadProfilePicture(multipartFile);
    }

    @PatchMapping("/updateBio")
    public void updateBio(@RequestParam("bio") String bio)  {
        userService.updateBio(bio);
    }

    @PatchMapping("/updateUsername")
    public void updateUsername(@RequestParam("username") String username)  {
        userService.updateUsername(username);
    }

    @PatchMapping("/updateEmail")
    public void updateEmail(@RequestParam("email") String email)  {
        userService.updateEmail(email);
    }

    @PatchMapping("/updatePassword")
    public void updatePassword(@RequestBody ChangePasswordDto password) {
        userService.updatePassword(password);
    }

    @PatchMapping
    public UpdatePersonalInfoDto update(@RequestBody UpdatePersonalInfoDto dto) {
        return userService.update(dto);
    }

    @DeleteMapping
    public void delete() {
        userService.delete();
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<ErrorResponse> checkPassword(@RequestParam("password") String password) {
        return userService.checkPassword(password);
    }



}
