package com.alumni.project.controller.connection;

import com.alumni.project.service.connection.UserConnectionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/connection")
@RequiredArgsConstructor
public class UserConnectionController {

    private final UserConnectionServiceImpl userConnectionService;
}
