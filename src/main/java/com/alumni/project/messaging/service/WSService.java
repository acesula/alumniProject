package com.alumni.project.messaging.service;

import com.alumni.project.messaging.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void notifyFrontEnd(final String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }

    public void notifyUser(String id, final String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);

        simpMessagingTemplate.convertAndSendToUser(id, "/topic/private-messages", responseMessage);
    }
}
