package com.alumni.project.security.services.auth;

import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.dto.LoginRequestDto;
import com.alumni.project.security.dto.TokenDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Override
    public TokenDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = (AuthUserDetail) authentication.getPrincipal();
        return new TokenDto(jwtUtil.generateTokenForUser(user));
    }


}
