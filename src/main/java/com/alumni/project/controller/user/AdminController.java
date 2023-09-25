package com.alumni.project.controller.user;

import com.alumni.project.dto.user.AdminUserInfoDto;
import com.alumni.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public List<AdminUserInfoDto> findAll() {
        return userService.findAll();
    }

    @PatchMapping("/changeAccountStatus/{id}")
    public void changeAccountStatus(@PathVariable UUID id, @RequestParam("status") boolean status) {
        userService.changeAccountStatus(id, status);
    }

    @PatchMapping("/updatePassword/{id}")
    public void updatePassword(@PathVariable UUID id, @RequestParam("password") String password) {
        userService.updatePasswordByAdmin(id, password);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody AdminUserInfoDto user) {
        userService.updateUserByAdmin(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.deleteByAdmin(id);
    }
}
