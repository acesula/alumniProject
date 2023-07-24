package com.alumni.project.security.config;

import com.alumni.project.dto.user.UserDto;
import com.alumni.project.service.mapping.MappingService;
import com.alumni.project.service.user.UserService;
import com.alumni.project.service.user.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DatabaseLoader {

    private final UserServiceImpl userService;
    private final MappingService mappingService;

    @PostConstruct
    void init(){createUser();}

    private void createUser(){
        if(!userService.findAll().isEmpty()){
            return;
        }
        var admin= UserDto.builder()
                .name("Adi")
                .surname("Cesula")
                .email("admin@gmail.com")
                .gender("Male")
                .birthDate(LocalDate.of(2001,6,4))
                .username("admin")
                .password("password")
                .role("ADMIN")
                .build();

        var user = UserDto.builder()
                .name("Adi")
                .surname("Cesula")
                .email("user@gmail.com")
                .gender("Male")
                .birthDate(LocalDate.of(2001, 6, 4))
                .username("user")
                .password("password")
                .role("USER")
                .build();

        for (UserDto userDto : Arrays.asList(admin, user)) {
            userService.register(userDto);
        }

    }
}
