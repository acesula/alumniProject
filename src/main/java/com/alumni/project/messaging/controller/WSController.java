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

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        service.notifyFrontEnd(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable String id, @RequestBody Message message) {
        service.notifyUser(id, message.getMessageContent());
    }
}
