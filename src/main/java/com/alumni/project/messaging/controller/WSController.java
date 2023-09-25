package com.alumni.project.messaging.controller;

import com.alumni.project.messaging.dto.Message;
import com.alumni.project.messaging.service.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ws-messaging")
@RequiredArgsConstructor
public class WSController {

    private final WSService service;

    @PostMapping
    public void sendPrivateMessage(@RequestBody Message message) {
        service.sendMessage(message);
    }
}
