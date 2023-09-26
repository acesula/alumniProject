package com.alumni.project.security.controller;

import com.alumni.project.dto.user.RegisterDto;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.security.dto.LoginRequestDto;
import com.alumni.project.security.dto.TokenDto;
import com.alumni.project.security.services.auth.AuthenticationService;
import com.alumni.project.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    public TokenDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<ErrorResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
