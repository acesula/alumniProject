package com.alumni.project.security.services.auth;

import com.alumni.project.security.dto.LoginRequestDto;
import com.alumni.project.security.dto.TokenDto;

public interface AuthenticationService {


    TokenDto login(LoginRequestDto loginRequestDto);
}
