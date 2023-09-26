package com.alumni.project.messaging.service;

import com.alumni.project.messaging.dto.Message;
import com.alumni.project.messaging.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(final Message message) {
        ResponseMessage response = new ResponseMessage(message.getSender(), message.getMessageContent());
        messagingTemplate.convertAndSend("/topic/" + message.getTopic(), response);
    }
}
