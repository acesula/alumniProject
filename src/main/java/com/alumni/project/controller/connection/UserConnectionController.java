package com.alumni.project.controller.connection;

import com.alumni.project.dal.entity.UserConnection;
import com.alumni.project.dto.connection.UserConnectionDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.connection.UserConnectionServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/connection")
@RequiredArgsConstructor
public class UserConnectionController {

    private final UserConnectionServiceImpl userConnectionService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> saveConnection(@PathVariable String username, @RequestBody UserConnection userConnection) {
        return userConnectionService.saveConnection(username, userConnection);
    }

    @GetMapping
    public List<UserConnectionDto> findAll() {
        return userConnectionService.findAll();
    }

    @DeleteMapping("/{username}")
    @Transactional
    public void deleteConnection(@PathVariable String username) {
        userConnectionService.delete(username);
    }
}
