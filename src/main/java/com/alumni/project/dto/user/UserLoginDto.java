package com.alumni.project.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class UserLoginDto {
    private String username;
    private String password;
}
