package com.alumni.project.security.controller;

import com.alumni.project.security.dto.LoginRequestDto;
import com.alumni.project.security.dto.TokenDto;
import com.alumni.project.security.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public TokenDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }
}
